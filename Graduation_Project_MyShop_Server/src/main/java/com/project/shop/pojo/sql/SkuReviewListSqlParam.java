package com.project.shop.pojo.sql;

import java.util.List;

public class SkuReviewListSqlParam {
    private Integer skuId;
    private String status;
    private List<Integer> reviewRateList;
    private String orderOf;
    private String orderType;
    private Integer index;
    private Integer size;

    @Override
    public String toString() {
        return "SkuReviewListSqlParam{" +
                "skuId=" + skuId +
                ", status='" + status + '\'' +
                ", reviewRateList=" + reviewRateList +
                ", orderOf='" + orderOf + '\'' +
                ", orderType='" + orderType + '\'' +
                ", index=" + index +
                ", size=" + size +
                '}';
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getReviewRateList() {
        return reviewRateList;
    }

    public void setReviewRateList(List<Integer> reviewRateList) {
        this.reviewRateList = reviewRateList;
    }

    public String getOrderOf() {
        return orderOf;
    }

    public void setOrderOf(String orderOf) {
        this.orderOf = orderOf;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
