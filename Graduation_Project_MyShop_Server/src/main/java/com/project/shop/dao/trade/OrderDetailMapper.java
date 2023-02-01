package com.project.shop.dao.trade;

import com.project.shop.pojo.po.trade.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderDetailMapper {

    @Update("update order_detail set hasReview = #{hasReviewed} where id = #{orderDetailId}")
    public Integer updateOrderDetailReviewed(@Param("orderDetailId")Integer orderDetailId,
                                             @Param("hasReviewed") Integer hasReviewed);

    @Select("select * from order_detail where id = #{orderDetailId}")
    public OrderDetail getOrderDetail(@Param("orderDetailId")Integer orderDetailId);
    public Integer addOrderDetailList(List<OrderDetail> orderDetailList);

    @Select("select MAX(id) from order_detail")
    public Integer getMaxIdOfOrderDetail();
}
