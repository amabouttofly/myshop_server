package com.project.shop.pojo.sql;

import com.project.shop.pojo.po.sku.SkuAttrValue;

import java.util.List;

public class SearchSkuListLimitPageWithOrderSqlParam {
    private Integer category3Id;
    private Integer tmId;
    private List<SkuAttrValue> skuAttrValueList;
    private Integer skuAttrValueNum;
    private Integer index;
    private Integer size;
    private Integer order;
    private String orderOf;

    @Override
    public String toString() {
        return "SearchSkuListLimitPageWithOrderSqlParam{" +
                "category3Id=" + category3Id +
                ", tmId=" + tmId +
                ", skuAttrValueList=" + skuAttrValueList +
                ", skuAttrValueNum=" + skuAttrValueNum +
                ", index=" + index +
                ", size=" + size +
                ", order=" + order +
                ", orderOf='" + orderOf + '\'' +
                '}';
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public Integer getTmId() {
        return tmId;
    }

    public void setTmId(Integer tmId) {
        this.tmId = tmId;
    }

    public List<SkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

    public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getOrderOf() {
        return orderOf;
    }

    public void setOrderOf(String orderOf) {
        this.orderOf = orderOf;
    }

    public Integer getSkuAttrValueNum() {
        return skuAttrValueNum;
    }

    public void setSkuAttrValueNum(Integer skuAttrValueNum) {
        this.skuAttrValueNum = skuAttrValueNum;
    }
}
