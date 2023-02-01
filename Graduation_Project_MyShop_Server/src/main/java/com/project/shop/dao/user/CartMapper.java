package com.project.shop.dao.user;

import com.project.shop.pojo.po.user.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {

    @Select("select * from cart_list where userId = #{userId} and skuId = #{skuId}")
    public Cart getCartByUserIdAndSkuId(@Param("userId")Integer userId,@Param("skuId")Integer skuId);

    @Select("select * from cart_list where userId = #{userId}")
    public List<Cart> getCartListByUserId(@Param("userId")Integer userId);

    public Integer setAllCartIsChecked(@Param("userId")Integer userId,@Param("isChecked")Integer isChecked);

    public Integer setCartIsChecked(@Param("userId")Integer userId,
                                    @Param("isChecked")Integer isChecked,
                                    @Param("skuId")Integer skuId);
    @Select("select * from cart_list where userId = #{userId} and isChecked = 1")
    public List<Cart> getAllCheckedCartListByUserId(@Param("userId")Integer userId);
    public Integer addCart(Cart cart);

    public Integer updateCart(Cart cart);

    public Integer deleteCart(@Param("userId")Integer userId, @Param("skuId")Integer skuId);

    public Integer deleteAllCheckedCart(@Param("userId")Integer userId);

    public Integer deleteCartList(List<Cart> cartList);
}
