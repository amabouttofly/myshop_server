package com.project.shop.pojo.bo;

import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.spu.SpuSaleAttrKey;
import com.project.shop.pojo.sql.CategoryViewSqlResult;

import java.util.HashMap;
import java.util.List;

public class SkuDetailInfoBo {
    private CategoryViewSqlResult categoryView;
    private List<SpuSaleAttrKey> spuSaleAttrKeyList;
    private Sku sku;

    @Override
    public String toString() {
        return "SkuDetailInfoBo{" +
                "categoryView=" + categoryView +
                ", spuSaleAttrKeyList=" + spuSaleAttrKeyList +
                ", sku=" + sku +
                '}';
    }

    public CategoryViewSqlResult getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(CategoryViewSqlResult categoryView) {
        this.categoryView = categoryView;
    }

    public List<SpuSaleAttrKey> getSpuSaleAttrKeyList() {
        return spuSaleAttrKeyList;
    }

    public void setSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        this.spuSaleAttrKeyList = spuSaleAttrKeyList;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }
}
