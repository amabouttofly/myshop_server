package com.project.shop.dao.attr;

import com.project.shop.pojo.po.attr.AttrValue;
import com.project.shop.pojo.sql.AttrValueSqlParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AttrValueMapper {

    public Integer addAttrValueList(List<AttrValue> attrValueList);


    // 没有使用的方法
    public Integer deleteAttrValueList(AttrValueSqlParam attrValueSqlParam);

    @Delete("delete from attr_value where attrId = #{attrId}")
    public Integer deleteAllAttrValue(@Param("attrId") Integer attrId);

    @Select("select MAX(id) from attr_value ")
    public Integer getMaxIdOfAttrValue();

}
