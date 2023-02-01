package com.project.shop.pojo.sql;

import com.project.shop.pojo.po.spu.SpuSaleAttrValue;

import java.util.List;

public class DeleteSpuSaleAttrValueSqlParam {


    private List<Integer> undeleteSpuSaleAttrValueIdList;
    private List<Integer> spuSaleAttrKeyIdList;

    public List<Integer> getUndeleteSpuSaleAttrValueIdList() {
        return undeleteSpuSaleAttrValueIdList;
    }

    public void setUndeleteSpuSaleAttrValueIdList(List<Integer> undeleteSpuSaleAttrValueIdList) {
        this.undeleteSpuSaleAttrValueIdList = undeleteSpuSaleAttrValueIdList;
    }

    public List<Integer> getSpuSaleAttrKeyIdList() {
        return spuSaleAttrKeyIdList;
    }

    public void setSpuSaleAttrKeyIdList(List<Integer> spuSaleAttrKeyIdList) {
        this.spuSaleAttrKeyIdList = spuSaleAttrKeyIdList;
    }
}
