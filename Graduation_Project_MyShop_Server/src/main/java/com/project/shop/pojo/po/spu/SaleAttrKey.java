package com.project.shop.pojo.po.spu;

public class SaleAttrKey {
    private Integer id;

    private String saleAttrKeyName;

    @Override
    public String toString() {
        return "SaleAttrKey{" +
                "id=" + id +
                ", saleAttrKeyName='" + saleAttrKeyName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleAttrKeyName() {
        return saleAttrKeyName;
    }

    public void setSaleAttrKeyName(String saleAttrKeyName) {
        this.saleAttrKeyName = saleAttrKeyName;
    }
}
