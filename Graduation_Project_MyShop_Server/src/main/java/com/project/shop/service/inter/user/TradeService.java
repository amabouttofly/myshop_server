package com.project.shop.service.inter.user;

import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.po.trade.OrderDetail;
import com.project.shop.pojo.req.product.SubmitOrderRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;

import java.util.List;

public interface TradeService {

    public OrderDetail getOrderDetail(Integer orderDetailId);

    public AboutUserResponse getUserTradeInfo(Integer userId);

    public AboutUserResponse addOrder(SubmitOrderRequest request, Integer userId);

    public Order getOrderByOrderId(Integer orderId);

    public AboutUserResponse getOrderList(Integer userId, Integer currentPage, Integer pageLimit);

    public Boolean deleteOrderById(Integer orderId);
}
