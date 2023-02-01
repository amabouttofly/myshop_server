package com.project.shop.pojo.bo;

import com.project.shop.pojo.po.sku.Sku;

import java.util.List;

public class LimitPageOfSkuBo {
    private Integer currentPage;
    private Integer pageLimit;
    private Integer itemsTotal;
    private Integer pagesTotal;
    private List<Sku> skuList;

    @Override
    public String toString() {
        return "LimitPageOfSkuBo{" +
                "currentPage=" + currentPage +
                ", pageLimit=" + pageLimit +
                ", itemsTotal=" + itemsTotal +
                ", pagesTotal=" + pagesTotal +
                ", skuList=" + skuList +
                '}';
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

    public Integer getItemsTotal() {
        return itemsTotal;
    }

    public void setItemsTotal(Integer itemsTotal) {
        this.itemsTotal = itemsTotal;
    }

    public Integer getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(Integer pagesTotal) {
        this.pagesTotal = pagesTotal;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
