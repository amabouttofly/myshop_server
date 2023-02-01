package com.project.shop.pojo.po.sku;

public class SkuSaleAttrValue {
    private Integer id;
    private Integer saleAttrKeyId;
    private String saleAttrKeyName;
    private Integer saleAttrValueId;
    private String saleAttrValueName;
    private Integer skuId;

    @Override
    public String toString() {
        return "SkuSaleAttrValue{" +
                "id=" + id +
                ", saleAttrKeyId=" + saleAttrKeyId +
                ", saleAttrKeyName='" + saleAttrKeyName + '\'' +
                ", saleAttrValueId=" + saleAttrValueId +
                ", saleAttrValueName='" + saleAttrValueName + '\'' +
                ", skuId=" + skuId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaleAttrKeyId() {
        return saleAttrKeyId;
    }

    public void setSaleAttrKeyId(Integer saleAttrKeyId) {
        this.saleAttrKeyId = saleAttrKeyId;
    }

    public String getSaleAttrKeyName() {
        return saleAttrKeyName;
    }

    public void setSaleAttrKeyName(String saleAttrKeyName) {
        this.saleAttrKeyName = saleAttrKeyName;
    }

    public Integer getSaleAttrValueId() {
        return saleAttrValueId;
    }

    public void setSaleAttrValueId(Integer saleAttrValueId) {
        this.saleAttrValueId = saleAttrValueId;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}
