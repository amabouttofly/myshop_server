package com.project.shop.pojo.po.trade;

public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer skuId;
    private String skuName;
    private String imageUrl;
    private String orderPrice;
    private Integer skuNum;
    private Integer hasStock;
    private Integer hasReview;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", skuNum=" + skuNum +
                ", hasStock=" + hasStock +
                ", hasReview=" + hasReview +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public Integer getHasStock() {
        return hasStock;
    }

    public void setHasStock(Integer hasStock) {
        this.hasStock = hasStock;
    }

    public Integer getHasReview() {
        return hasReview;
    }

    public void setHasReview(Integer hasReview) {
        this.hasReview = hasReview;
    }
}
