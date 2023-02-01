package com.project.shop.pojo.po.spu;

public class SpuSaleAttrValue {

    private Integer id;
    private Integer isChecked;
    private String saleAttrValueName;

    private Integer spuSaleAttrKeyId;

    @Override
    public String toString() {
        return "SpuSaleAttrValue{" +
                "id=" + id +
                ", isChecked=" + isChecked +
                ", saleAttrValueName='" + saleAttrValueName + '\'' +
                ", spuSaleAttrKeyId=" + spuSaleAttrKeyId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }

    public Integer getSpuSaleAttrKeyId() {
        return spuSaleAttrKeyId;
    }

    public void setSpuSaleAttrKeyId(Integer spuSaleAttrKeyId) {
        this.spuSaleAttrKeyId = spuSaleAttrKeyId;
    }
}
