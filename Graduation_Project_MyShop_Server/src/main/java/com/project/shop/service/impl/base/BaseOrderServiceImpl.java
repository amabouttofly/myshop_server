package com.project.shop.service.impl.base;

import com.project.shop.dao.trade.OrderDetailMapper;
import com.project.shop.dao.trade.OrderMapper;
import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.po.trade.OrderDetail;
import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.res.userAbout.UserReplyResponse;
import com.project.shop.service.inter.base.BaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BaseOrderServiceImpl implements BaseOrderService {
    private OrderDetailMapper orderDetailMapper;
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> getOrderListByStatus(String status, Integer currentPage, Integer pageLimit) {

        Integer itemsTotal = orderMapper.getItemsTotalInStatus(status);
        if (itemsTotal==null) itemsTotal=0;
        if (pageLimit < 1) pageLimit = 1;
        if (currentPage < 1) currentPage = 1;

        while (pageLimit * (currentPage - 1) >= itemsTotal) {
            if(currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;
        List<Order> orderList = orderMapper.getOrderLimitListByStatus(status, index ,size);

        Map<String,Object> data = new HashMap<>();
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal", itemsTotal);
        data.put("pagesTotal",pagesTotal);
        data.put("orderList",orderList);
        return data;
    }

    @Override
    public OrderDetail getOrderDetailById(Integer orderDetailId) {
        return orderDetailMapper.getOrderDetail(orderDetailId);
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
