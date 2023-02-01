package com.project.shop.pojo.po.attr;

import java.util.List;

public class AttrKey {

    private Integer id;
    private String attrKeyName;
    private Integer categoryId;
    private Integer categoryLevel;

    private List<AttrValue> attrValueList;

    @Override
    public String toString() {
        return "AttrKey{" +
                "id=" + id +
                ", attrKeyName='" + attrKeyName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryLevel=" + categoryLevel +
                ", attrValueList=" + attrValueList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrKeyName() {
        return attrKeyName;
    }

    public void setAttrKeyName(String attrKeyName) {
        this.attrKeyName = attrKeyName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public List<AttrValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValue> attrValueList) {
        this.attrValueList = attrValueList;
    }
}
