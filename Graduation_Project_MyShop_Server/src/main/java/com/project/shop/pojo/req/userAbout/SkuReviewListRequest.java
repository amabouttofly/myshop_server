package com.project.shop.pojo.req.userAbout;

import java.util.List;

public class SkuReviewListRequest {
    private List<Integer> reviewRateList;
    private String order;
    private Integer skuId;
    private Integer currentPage;
    private Integer pageLimit;

    @Override
    public String toString() {
        return "SkuReviewListRequest{" +
                "reviewRateList=" + reviewRateList +
                ", order='" + order + '\'' +
                ", skuId=" + skuId +
                ", currentPage=" + currentPage +
                ", pageLimit=" + pageLimit +
                '}';
    }

    public List<Integer> getReviewRateList() {
        return reviewRateList;
    }

    public void setReviewRateList(List<Integer> reviewRateList) {
        this.reviewRateList = reviewRateList;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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
}
