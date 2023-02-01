package com.project.shop.service.impl.user;

import com.project.shop.dao.sku.SkuMapper;
import com.project.shop.dao.user.CartMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.user.Cart;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CartServiceImpl implements CartService {
    private SkuMapper skuMapper;
    private CartMapper cartMapper;

    @Override
    public AboutUserResponse addCartBySkuIdAndNum(Integer skuId, Integer skuNum, Integer userId) {
        AboutUserResponse response = new AboutUserResponse();
        Sku sku = skuMapper.getSkuWithoutPropList(skuId);
        if (sku == null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("skuId错误");
            return response;
        }
        Cart repeatCart = cartMapper.getCartByUserIdAndSkuId(userId, skuId);
        if (repeatCart != null){
            System.out.println("用户购物车已存在sku,进行更新");
            if (repeatCart.getSkuNum() + skuNum < 1){
                response.setCode(ResponseCodeConstant.ServiceFailedCode);
                response.setMessage("sku数量不能为负");
                return response;
            }
            repeatCart.setSkuNum(repeatCart.getSkuNum() + skuNum);
            float newCartPrice = Float.parseFloat(repeatCart.getSkuPrice()) * repeatCart.getSkuNum();
            repeatCart.setCartPrice(Float.toString(newCartPrice));
            if (cartMapper.updateCart(repeatCart) == 1){
                response.setCode(UserStaticData.PassCode);
                response.setMessage("更新购物车成功");
                return response;
            }
        }
        Cart cart = new Cart(sku);
        cart.setUserId(userId);
        cart.setIsChecked(0);
        float cartPrice = Float.parseFloat(sku.getPrice()) * skuNum;
        cart.setCartPrice(Float.toString(cartPrice));
        cart.setSkuNum(skuNum);
        if (cartMapper.addCart(cart) == 1){
            response.setCode(UserStaticData.PassCode);
            response.setMessage("添加购物车成功");
            return response;
        }
        response.setCode(ResponseCodeConstant.ServiceFailedCode);
        response.setMessage("添加购物车失败");
        return response;
    }

    @Override
    public List<Cart> getCartListByUserId(Integer userId) {
        return cartMapper.getCartListByUserId(userId);
    }

    @Override
    public Boolean checkAllCart(Integer userId, Integer isChecked) {
        if (isChecked >= 1){
            isChecked = 1;
        }else {
            isChecked = 0;
        }
        Integer changeNum = cartMapper.setAllCartIsChecked(userId, isChecked);
        System.out.println("更改选择数量:"+changeNum);
        return true;
    }

    @Override
    public Boolean checkCart(Integer userId, Integer isChecked, Integer skuId) {
        if (isChecked >= 1){
            isChecked = 1;
        }else {
            isChecked = 0;
        }
        Integer changeNum = cartMapper.setCartIsChecked(userId, isChecked, skuId);
        System.out.println("更改选择数量:"+changeNum);
        return true;
    }

    @Override
    public Boolean deleteCart(Integer userId, Integer skuId) {
        Integer changeNum = cartMapper.deleteCart(userId,skuId);
        System.out.println("删除数量:"+changeNum);
        return true;
    }

    @Override
    public Boolean deleteAllCheckedCart(Integer userId) {
        Integer changeNum = cartMapper.deleteAllCheckedCart(userId);
        System.out.println("删除数量:"+changeNum);
        return true;
    }

    @Autowired
    public void setSkuMapper(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }
    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
}
