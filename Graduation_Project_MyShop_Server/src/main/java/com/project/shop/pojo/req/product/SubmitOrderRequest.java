package com.project.shop.pojo.req.product;

import com.project.shop.pojo.po.user.Cart;
import com.project.shop.pojo.po.user.UserAddress;

import java.util.List;

public class SubmitOrderRequest {
    private UserAddress userAddress;
    private List<Cart> cartList;

    private String paymentWay;
    private String orderComment;

    @Override
    public String toString() {
        return "SubmitOrderRequest{" +
                "userAddress=" + userAddress +
                ", cartList=" + cartList +
                ", paymentWay='" + paymentWay + '\'' +
                ", orderComment='" + orderComment + '\'' +
                '}';
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }
}
