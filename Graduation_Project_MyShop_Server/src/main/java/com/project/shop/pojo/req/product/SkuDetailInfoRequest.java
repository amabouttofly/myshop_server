package com.project.shop.pojo.req.product;

import java.util.Arrays;
import java.util.List;

public class SkuDetailInfoRequest {
    private List<Integer> saleAttrValueIds;

    @Override
    public String toString() {
        return "SkuDetailInfoRequest{" +
                "saleAttrValueIds=" + saleAttrValueIds +
                '}';
    }

    public List<Integer> getSaleAttrValueIds() {
        return saleAttrValueIds;
    }

    public void setSaleAttrValueIds(List<Integer> saleAttrValueIds) {
        this.saleAttrValueIds = saleAttrValueIds;
    }
}
