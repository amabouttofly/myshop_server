package com.project.shop.pojo.sql;

import com.project.shop.pojo.po.attr.AttrValue;

import java.util.List;

public class AttrValueSqlParam {
    public Integer attrId;
    public List<AttrValue> attrValueList;

    @Override
    public String toString() {
        return "AttrValueSqlParam{" +
                "attrId=" + attrId +
                ", attrValueList=" + attrValueList +
                '}';
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public List<AttrValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValue> attrValueList) {
        this.attrValueList = attrValueList;
    }
}
