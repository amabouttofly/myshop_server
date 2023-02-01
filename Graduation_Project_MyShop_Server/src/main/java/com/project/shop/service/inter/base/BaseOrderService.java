package com.project.shop.service.inter.base;

import com.project.shop.pojo.po.trade.OrderDetail;

import java.util.Map;

public interface BaseOrderService {

    public Map<String, Object> getOrderListByStatus(String status, Integer currentPage, Integer pageLimit);

    public OrderDetail getOrderDetailById(Integer orderDetailId);
}
