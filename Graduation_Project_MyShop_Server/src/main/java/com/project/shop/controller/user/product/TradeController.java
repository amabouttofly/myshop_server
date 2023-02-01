package com.project.shop.controller.user.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.dao.trade.OrderMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.req.product.SubmitOrderRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.user.TradeService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/user/product/trade")
@RestController
public class TradeController {

    private TradeService tradeService;
    private OrderMapper orderMapper;

    @GetMapping("/getUserTrade")
    public String getUserTrade(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = tradeService.getUserTradeInfo(user.getId());
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@RequestBody SubmitOrderRequest requestData,HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到提交订单请求,数据为:"+request);
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = tradeService.addOrder(requestData, user.getId());
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getOrderInfo/{orderId}")
    public String getOrderInfo(@PathVariable("orderId")Integer orderId) throws JsonProcessingException {
        Order order = orderMapper.getOrderById(orderId);
        AboutUserResponse response = new AboutUserResponse();
        if (order == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("订单Id不存在");
        }else {
            response.setCode(UserStaticData.PassCode);
            response.setData(order);
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getOrderList/{currentPage}/{pageLimit}")
    public String getOrderList(@PathVariable("currentPage")Integer currentPage,
                               @PathVariable("pageLimit")Integer pageLimit,
                               HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = tradeService.getOrderList(user.getId(),currentPage,pageLimit);
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setTradeService(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
