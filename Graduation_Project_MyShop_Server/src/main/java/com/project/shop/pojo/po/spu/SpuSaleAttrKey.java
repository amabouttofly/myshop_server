package com.project.shop.pojo.po.spu;

import java.util.List;

public class SpuSaleAttrKey {
    private Integer id;
    private Integer spuId;

    private Integer saleAttrKeyId;
    private String saleAttrKeyName;
    private List<SpuSaleAttrValue> spuSaleAttrValueList;

    @Override
    public String toString() {
        return "SpuSaleAttrKey{" +
                "id=" + id +
                ", spuId=" + spuId +
                ", saleAttrKeyId=" + saleAttrKeyId +
                ", saleAttrKeyName='" + saleAttrKeyName + '\'' +
                ", spuSaleAttrValueList=" + spuSaleAttrValueList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSaleAttrKeyName() {
        return saleAttrKeyName;
    }

    public void setSaleAttrKeyName(String saleAttrKeyName) {
        this.saleAttrKeyName = saleAttrKeyName;
    }

    public List<SpuSaleAttrValue> getSpuSaleAttrValueList() {
        return spuSaleAttrValueList;
    }

    public void setSpuSaleAttrValueList(List<SpuSaleAttrValue> spuSaleAttrValueList) {
        this.spuSaleAttrValueList = spuSaleAttrValueList;
    }

    public Integer getSaleAttrKeyId() {
        return saleAttrKeyId;
    }

    public void setSaleAttrKeyId(Integer saleAttrKeyId) {
        this.saleAttrKeyId = saleAttrKeyId;
    }
}
