package com.project.shop.controller.user.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.user.CartService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/user/product/cart")
@RestController
public class CartController {

    private CartService cartService;

    @PostMapping("/changeCartSkuNum/{skuId}/{skuNum}")
    public String changeCartSkuNum(@PathVariable("skuId")Integer skuId,
                                   @PathVariable("skuNum")Integer skuNum,
                                   HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到更新购物车sku数量请求,skuId:"+skuId+"skuNum:"+skuNum);
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        System.out.println("用户信息为:"+user);
        AboutUserResponse response = cartService.addCartBySkuIdAndNum(skuId,skuNum,user.getId());
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getCartList")
    public String getCartList(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData(cartService.getCartListByUserId(user.getId()));
        System.out.println("返回数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/checkAllCart/{isChecked}")
    public String checkAllCart(@PathVariable("isChecked")Integer isChecked, HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = new AboutUserResponse();
        if (cartService.checkAllCart(user.getId(), isChecked)){
            response.setCode(UserStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("更新失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/checkCart/{skuId}/{isChecked}")
    public String checkCart(@PathVariable("skuId")Integer skuId,
                            @PathVariable("isChecked")Integer isChecked,
                            HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = new AboutUserResponse();
        if (cartService.checkCart(user.getId(),isChecked,skuId)){
            response.setCode(UserStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("更新失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping("/deleteCart/{skuId}")
    public String deleteCart(@PathVariable("skuId")Integer skuId,HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        AboutUserResponse response = new AboutUserResponse();
        if (cartService.deleteCart(user.getId(),skuId)){
            response.setCode(UserStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("更新失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping ("/deleteAllCheckedCart")
    public String deleteAllCheckedCart(HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到删除所有选中商品请求");
        AboutUserResponse response = new AboutUserResponse();
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        if (cartService.deleteAllCheckedCart(user.getId())){
            response.setCode(UserStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("更新失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
