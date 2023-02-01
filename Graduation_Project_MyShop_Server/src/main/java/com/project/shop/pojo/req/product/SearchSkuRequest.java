package com.project.shop.pojo.req.product;

import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.po.sku.SkuAttrValue;

import java.util.List;

public class SearchSkuRequest {
    private Integer category1Id;

    private Integer category2Id;
    private Integer category3Id;
    private String categoryName;
    private String keyword;
    private Trademark trademark;
    private List<SkuAttrValue> skuAttrValueList;
    private Integer order;
    private String orderOf;
    private Integer currentPage;
    private Integer pageLimit;

    @Override
    public String toString() {
        return "SearchSkuRequest{" +
                "category1Id=" + category1Id +
                ", category2Id=" + category2Id +
                ", category3Id=" + category3Id +
                ", categoryName='" + categoryName + '\'' +
                ", keyword='" + keyword + '\'' +
                ", trademark=" + trademark +
                ", skuAttrValueList=" + skuAttrValueList +
                ", order=" + order +
                ", orderOf='" + orderOf + '\'' +
                ", currentPage=" + currentPage +
                ", pageLimit=" + pageLimit +
                '}';
    }

    public Integer getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    public Integer getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public List<SkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

    public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getOrderOf() {
        return orderOf;
    }

    public void setOrderOf(String orderOf) {
        this.orderOf = orderOf;
    }
}
