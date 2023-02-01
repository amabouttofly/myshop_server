package com.project.shop.pojo.bo;

import com.project.shop.pojo.po.attr.Trademark;

import java.util.List;

public class LimitPageOfTrademarkBO {
    private List<Trademark> trademarkList;
    private Integer currentPage;
    private Integer pageLimit;
    private Integer itemsTotal;
    private Integer pagesTotal;

    @Override
    public String toString() {
        return "LimitPageOfTrademarkBO{" +
                "trademarkList=" + trademarkList +
                ", currentPage=" + currentPage +
                ", pageLimit=" + pageLimit +
                ", itemsTotal=" + itemsTotal +
                ", pagesTotal=" + pagesTotal +
                '}';
    }

    public List<Trademark> getTrademarkList() {
        return trademarkList;
    }

    public void setTrademarkList(List<Trademark> trademarkList) {
        this.trademarkList = trademarkList;
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
}
