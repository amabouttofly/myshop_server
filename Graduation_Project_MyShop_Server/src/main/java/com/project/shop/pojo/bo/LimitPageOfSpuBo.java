package com.project.shop.pojo.bo;

import com.project.shop.pojo.po.spu.Spu;

import java.util.List;

public class LimitPageOfSpuBo {
    private Integer currentPage;
    private Integer pageLimit;
    private Integer itemsTotal;
    private Integer pagesTotal;

    private List<Spu> spuList;

    @Override
    public String toString() {
        return "LimitPageOfSpuBo{" +
                "currentPage=" + currentPage +
                ", pageLimit=" + pageLimit +
                ", itemsTotal=" + itemsTotal +
                ", pagesTotal=" + pagesTotal +
                ", spuList=" + spuList +
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

    public List<Spu> getSpuList() {
        return spuList;
    }

    public void setSpuList(List<Spu> spuList) {
        this.spuList = spuList;
    }
}
