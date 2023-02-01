package com.project.shop.dao.trade;

import com.project.shop.pojo.bo.LimitPageWithOrderOfSkuBo;
import com.project.shop.pojo.po.trade.Order;
import com.project.shop.pojo.po.user.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderMapper {

    @Select("select count(*) from order_list where orderStatus = #{status}")
    public Integer getItemsTotalInStatus(@Param("status")String status);

    @Select("select * from order_list where orderStatus = #{status} limit #{index}, #{size}")
    public List<Order> getOrderLimitListByStatus(@Param("status")String status,
                                                 @Param("index")Integer index,
                                                 @Param("size")Integer size);

    @Select("select count(*) from order_list where userId = #{userId}")
    public Integer getOrderItemsTotal(@Param("userId")Integer userId);

    public List<Order> getOrderListByUserId(@Param("userId")Integer userId,
                                            @Param("index")Integer index,
                                            @Param("size")Integer size);
    public Order getOrderById(@Param("orderId") Integer orderId);

    @Select("select * from order_list where id = #{orderId}")
    public Order getOrderWithoutDetailListById(@Param("orderId") Integer orderId);
    public Integer addOrder(Order order);

    public Integer updatePaidOrder(Order order);

    @Delete("delete from order_list where id = #{orderId}")
    public Integer deleteOrderById(@Param("orderId") Integer orderId);
}
