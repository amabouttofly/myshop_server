package com.project.shop.pojo.po.attr;

public class AttrValue {

    private Integer id;
    private String attrValueName;
    private Integer attrId;

    @Override
    public String toString() {
        return "AttrValue{" +
                "id=" + id +
                ", attrValueName='" + attrValueName + '\'' +
                ", attrId=" + attrId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
}
