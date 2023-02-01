package com.project.shop.pojo.po.user;

import com.project.shop.pojo.po.sku.Sku;

public class Cart {
    private Integer id;
    private Integer userId;
    private Integer isChecked;
    private String cartPrice;
    private Integer skuNum;
    private Integer skuId;
    private String skuName;
    private String skuPrice;
    private String imageUrl;

    public Cart() {
    }

    public Cart(Sku sku){
        this.skuId = sku.getId();
        this.skuName = sku.getSkuName();
        this.skuPrice = sku.getPrice();
        this.imageUrl = sku.getSkuDefaultImage();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", isChecked=" + isChecked +
                ", cartPrice='" + cartPrice + '\'' +
                ", skuNum=" + skuNum +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuPrice='" + skuPrice + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
