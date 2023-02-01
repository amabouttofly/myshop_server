package com.project.shop.service.inter.user;

import com.project.shop.pojo.po.user.Cart;
import com.project.shop.pojo.res.admin.AboutUserResponse;

import java.util.List;

public interface CartService {

    public AboutUserResponse addCartBySkuIdAndNum(Integer skuId, Integer skuNum, Integer userId);

    public List<Cart> getCartListByUserId(Integer userId);

    public Boolean checkAllCart(Integer userId, Integer isChecked);

    public Boolean checkCart(Integer userId, Integer isChecked, Integer skuId);

    public Boolean deleteCart(Integer userId, Integer skuId);

    public Boolean deleteAllCheckedCart(Integer userId);
}
