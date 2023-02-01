package com.project.shop.pojo.po.sku;

public class SkuAttrValue {
    private Integer id;
    private Integer attrKeyId;
    private String attrKeyName;
    private Integer attrValueId;
    private String attrValueName;
    private Integer skuId;

    @Override
    public String toString() {
        return "SkuAttrValue{" +
                "id=" + id +
                ", attrKeyId=" + attrKeyId +
                ", attrKeyName='" + attrKeyName + '\'' +
                ", attrValueId=" + attrValueId +
                ", attrValueName='" + attrValueName + '\'' +
                ", skuId=" + skuId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
    }

    public String getAttrKeyName() {
        return attrKeyName;
    }

    public void setAttrKeyName(String attrKeyName) {
        this.attrKeyName = attrKeyName;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}
