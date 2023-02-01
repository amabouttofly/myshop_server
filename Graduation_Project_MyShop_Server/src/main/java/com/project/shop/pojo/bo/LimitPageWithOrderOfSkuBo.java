package com.project.shop.pojo.bo;

import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.po.sku.Sku;

import java.util.List;

public class LimitPageWithOrderOfSkuBo {
    private List<Trademark> trademarkList;
    private List<AttrKey> attrKeyList;
    private List<Sku> skuList;
    private Integer currentPage;
    private Integer pageLimit;
    private Integer itemsTotal;
    private Integer pagesTotal;

    @Override
    public String toString() {
        return "LimitPageWithOrderOfSkuBo{" +
                "trademarkList=" + trademarkList +
                ", attrKeyList=" + attrKeyList +
                ", skuList=" + skuList +
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

    public List<AttrKey> getAttrKeyList() {
        return attrKeyList;
    }

    public void setAttrKeyList(List<AttrKey> attrKeyList) {
        this.attrKeyList = attrKeyList;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
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
