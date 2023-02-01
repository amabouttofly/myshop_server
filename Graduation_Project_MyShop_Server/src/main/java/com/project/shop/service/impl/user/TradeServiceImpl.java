package com.project.shop.service.impl.user;

import com.project.shop.dao.trade.OrderDetailMapper;
import com.project.shop.dao.trade.OrderMapper;
import com.project.shop.dao.user.CartMapper;
import com.project.shop.dao.user.UserAddressMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.order.OrderConstant;
import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.po.trade.OrderDetail;
import com.project.shop.pojo.po.user.Cart;
import com.project.shop.pojo.po.user.UserAddress;
import com.project.shop.pojo.req.product.SubmitOrderRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.user.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.*;

@Transactional
@Service
public class TradeServiceImpl implements TradeService {

    private UserAddressMapper userAddressMapper;
    private CartMapper cartMapper;
    private OrderMapper orderMapper;
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail getOrderDetail(Integer orderDetailId) {
        return orderDetailMapper.getOrderDetail(orderDetailId);
    }

    @Override
    public AboutUserResponse getUserTradeInfo(Integer userId) {
        AboutUserResponse response = new AboutUserResponse();
        List<Cart> cartList = cartMapper.getAllCheckedCartListByUserId(userId);
        if (cartList == null || cartList.size() == 0){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("用户未选中商品");
            return response;
        }
        List<UserAddress> userAddressList = userAddressMapper.getUserAddressListByUserId(userId);
        Map<String,Object> data = new HashMap<>();
        data.put("userAddressList", userAddressList);
        data.put("productList", cartList);
        response.setCode(UserStaticData.PassCode);
        response.setData(data);
        return response;
    }

    @Override
    public AboutUserResponse getOrderList(Integer userId, Integer currentPage, Integer pageLimit) {
        AboutUserResponse response = new AboutUserResponse();
        Integer itemsTotal = orderMapper.getOrderItemsTotal(userId);
        if (currentPage < 1) currentPage = 1;
        if (pageLimit < 1) pageLimit = 1;
        if (itemsTotal == null) itemsTotal = 0;
        while ((currentPage - 1) * pageLimit >= itemsTotal) {
            if (currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;
        System.out.println(index+"---"+currentPage);
        List<Order> orderList = orderMapper.getOrderListByUserId(userId,index,size);
        Map<String, Object> data = new HashMap<>();
        data.put("orderList", orderList);
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal",itemsTotal);
        data.put("pagesTotal",pagesTotal);
        response.setCode(UserStaticData.PassCode);
        response.setData(data);
        return response;
    }

    @Override
    public AboutUserResponse addOrder(SubmitOrderRequest request, Integer userId) {
        AboutUserResponse response = new AboutUserResponse();
        if (request.getCartList() == null
                || request.getCartList().size()==0
                || request.getUserAddress() == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("数据不完整");
            return response;
        }
        List<Cart> cartList = request.getCartList();
        UserAddress userAddress = request.getUserAddress();
        Order order = new Order();
        order.setOrderStatus(OrderConstant.unpaid);
        order.setPaymentWay(OrderConstant.online);
        float totalPrice = 0;
        for (Cart cart: cartList) {
            totalPrice = totalPrice + Float.parseFloat(cart.getCartPrice());
        }
        order.setTotalPrice(Float.toString(totalPrice));
        order.setOrderComment(request.getOrderComment());
        DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
        Date date = new Date();
        order.setCreateTime(mediumFormat.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,OrderConstant.effectiveDateNum);
        order.setExpireTime(mediumFormat.format(calendar.getTime()));
        order.setConsignee(userAddress.getConsignee());
        order.setConsigneeTel(userAddress.getPhone());
        order.setDeliveryAddress(userAddress.getUserAddress());
        order.setUserId(userId);
        if (orderMapper.addOrder(order) == 0) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("订单添加失败");
            return response;
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Integer maxId = orderDetailMapper.getMaxIdOfOrderDetail();
        if (maxId == null) {
            maxId = 0;
        }
        for (Cart cart: cartList) {
            maxId++;
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(maxId);
            orderDetail.setOrderId(order.getId());
            orderDetail.setSkuId(cart.getSkuId());
            orderDetail.setSkuName(cart.getSkuName());
            orderDetail.setImageUrl(cart.getImageUrl());
            orderDetail.setOrderPrice(cart.getCartPrice());
            orderDetail.setSkuNum(cart.getSkuNum());
            orderDetail.setHasStock(OrderConstant.hasStock);
            orderDetailList.add(orderDetail);
        }
        Integer addNum = orderDetailMapper.addOrderDetailList(orderDetailList);
        System.out.println("订单详情插入数量:"+addNum);
        Integer deleteNum = cartMapper.deleteCartList(cartList);
        System.out.println("删除订单详情数量:"+deleteNum);
        response.setCode(UserStaticData.PassCode);
        response.setMessage("提交订单成功");
        response.setData(order.getId());
        return response;
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public Boolean deleteOrderById(Integer orderId) {
        return orderMapper.deleteOrderById(orderId) == 1;
    }

    @Autowired
    public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    @Autowired
    public void setOrderDetailMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }
}
