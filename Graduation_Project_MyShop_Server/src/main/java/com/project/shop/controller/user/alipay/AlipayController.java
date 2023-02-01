package com.project.shop.controller.user.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.common.MyAlipayConfigProp;
import com.project.shop.dao.trade.OrderMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.order.OrderConstant;
import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AlipayController {

    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT = "json";
    private static final String CHARSET = "UTF8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    private static final String notifyPath = "/api/alipay/notify";

    private MyAlipayConfigProp alipayConfigProp;
    private OrderMapper orderMapper;

    @GetMapping("/api/user/alipay/pay/{orderId}")
    public void pay(@PathVariable("orderId")Integer orderId, HttpServletResponse servletResponse) throws IOException, AlipayApiException {

        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(GATEWAY_URL);
        alipayConfig.setAppId(alipayConfigProp.getAppId());
        alipayConfig.setPrivateKey(alipayConfigProp.getAppPrivateKey());
        alipayConfig.setFormat(FORMAT);
        alipayConfig.setAlipayPublicKey(alipayConfigProp.getAlipayPublicKey());
        alipayConfig.setCharset(CHARSET);
        alipayConfig.setSignType(SIGN_TYPE);

        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest(); // 发送请求的 Request类
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        Order order = orderMapper.getOrderById(orderId);
        System.out.println("认为orderId一定正确,不会查询为空");

        model.setOutTradeNo(String.valueOf(order.getId())); // 我们自己生成的订单编号

        // String totalAmount = String.valueOf(Double.parseDouble(order.getTotalPrice()));
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String totalAmount = numberFormat.format(Double.parseDouble(order.getTotalPrice()));
        System.out.println("价格为:"+totalAmount);
        model.setTotalAmount(totalAmount); // 订单的总金额
        model.setSubject(OrderConstant.businessName); // 支付的名称
        model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 固定配置
        model.setQrPayMode("0"); // 0：订单码-简约前置模式，对应 iframe 宽度不能小于600px，高度不能小于300px
        request.setBizModel(model);
        request.setNotifyUrl(alipayConfigProp.getNotifyUrl() + notifyPath);

        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
        String form = "";
        if (response.isSuccess()) {
            System.out.println("调用成功");
            form = response.getBody(); // 调用SDK生成表单
        } else {
            System.out.println("调用失败");
            return;
        }

        // 执行请求，拿到响应的结果，返回给浏览器
        servletResponse.setContentType("text/html;charset=" + CHARSET);
        AboutUserResponse userResponse = new AboutUserResponse();
        userResponse.setCode(UserStaticData.PassCode);
        userResponse.setData(form);
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("application/json");
        servletResponse.getWriter().write(JsonUtils.getJsonString(userResponse));
        servletResponse.getWriter().flush();
        servletResponse.getWriter().close();
    }


    @GetMapping("/api/user/alipay/getOrderStatus/{orderId}")
    public String getOrderStatus(@PathVariable("orderId")Integer orderId) throws JsonProcessingException, AlipayApiException {
        System.out.println("收到订单查询请求");
        AboutUserResponse userResponse = new AboutUserResponse();
        Order order = orderMapper.getOrderWithoutDetailListById(orderId);
        if (order == null){
            userResponse.setCode(ResponseCodeConstant.ServiceFailedCode);
            userResponse.setMessage("订单id不存在");
            return JsonUtils.getJsonString(userResponse);
        }
        if (order.getOrderStatus().equals(OrderConstant.paid)){
            userResponse.setCode(UserStaticData.PassCode);
            userResponse.setMessage("订单支付成功");
            userResponse.setData(order.getOrderStatus());
            return JsonUtils.getJsonString(userResponse);
        }
        if (order.getOrderStatus().equals(OrderConstant.unpaid)){
            AlipayConfig alipayConfig = new AlipayConfig();
            alipayConfig.setServerUrl(GATEWAY_URL);
            alipayConfig.setAppId(alipayConfigProp.getAppId());
            alipayConfig.setPrivateKey(alipayConfigProp.getAppPrivateKey());
            alipayConfig.setFormat(FORMAT);
            alipayConfig.setAlipayPublicKey(alipayConfigProp.getAlipayPublicKey());
            alipayConfig.setCharset(CHARSET);
            alipayConfig.setSignType(SIGN_TYPE);
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", String.valueOf(order.getId()));
            request.setBizContent(bizContent.toString());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(!response.isSuccess()){
                System.out.println("调用失败");
                userResponse.setCode(ResponseCodeConstant.ServiceFailedCode);
                userResponse.setMessage("查询订单状态失败");
                return JsonUtils.getJsonString(userResponse);
            }
            System.out.println("调用成功");
            if (response.getTradeStatus().equals("WAIT_BUYER_PAY")){
                userResponse.setCode(UserStaticData.PassCode);
                userResponse.setMessage("交易创建，等待买家付款");
                return JsonUtils.getJsonString(userResponse);
            }
            if (response.getTradeStatus().equals("TRADE_CLOSED")){
                userResponse.setCode(UserStaticData.PassCode);
                userResponse.setMessage("未付款交易超时关闭，或支付完成后全额退款");
                return JsonUtils.getJsonString(userResponse);
            }
            if (response.getTradeStatus().equals("TRADE_SUCCESS")){

                order.setOrderStatus(OrderConstant.paid);
                order.setAlipayTradeNo(response.getTradeNo());
                DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
                Date date = new Date();
                order.setPayTime(mediumFormat.format(date));
                orderMapper.updatePaidOrder(order);

                userResponse.setCode(UserStaticData.PassCode);
                userResponse.setMessage("交易支付成功");
                userResponse.setData(order.getOrderStatus());
                return JsonUtils.getJsonString(userResponse);
            }

        }
        System.out.println("对查询的订单状态,只有paid和unpaid会做出相应操作");
        return null;
    }


    @PostMapping("/api/alipay/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        // 支付宝的异步通知回调,可以不用返回数据
        System.out.println("=========支付宝异步回调========");
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("收到支付宝异步通知,交易支付成功");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayConfigProp.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
                Order order = orderMapper.getOrderWithoutDetailListById(Integer.parseInt(outTradeNo));
                if (order != null) {
                    order.setOrderStatus(OrderConstant.paid);
                    order.setAlipayTradeNo(alipayTradeNo);
                    DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
                    Date date = new Date();
                    order.setPayTime(mediumFormat.format(date));
                    orderMapper.updatePaidOrder(order);
                }
            }
        }
        return "success";
    }

    @Autowired
    public void setAlipayConfigProp(MyAlipayConfigProp alipayConfigProp) {
        this.alipayConfigProp = alipayConfigProp;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
