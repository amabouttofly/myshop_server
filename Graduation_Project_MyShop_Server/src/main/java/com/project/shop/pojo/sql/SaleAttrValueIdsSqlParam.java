package com.project.shop.pojo.sql;

import java.util.List;

public class SaleAttrValueIdsSqlParam {
    private List<Integer> saleAttrValueIds;
    private Integer saleAttrValueIdsSize;

    @Override
    public String toString() {
        return "SaleAttrValueIdsSqlParam{" +
                "saleAttrValueIds=" + saleAttrValueIds +
                ", saleAttrValueIdsSize=" + saleAttrValueIdsSize +
                '}';
    }

    public List<Integer> getSaleAttrValueIds() {
        return saleAttrValueIds;
    }

    public void setSaleAttrValueIds(List<Integer> saleAttrValueIds) {
        this.saleAttrValueIds = saleAttrValueIds;
    }

    public Integer getSaleAttrValueIdsSize() {
        return saleAttrValueIdsSize;
    }

    public void setSaleAttrValueIdsSize(Integer saleAttrValueIdsSize) {
        this.saleAttrValueIdsSize = saleAttrValueIdsSize;
    }
}
