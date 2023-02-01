package com.project.shop.dao.attr;

import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.sql.SearchSkuListLimitPageWithOrderSqlParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AttrMapper {


    public List<AttrKey> getAttrKeyListInSkuList(SearchSkuListLimitPageWithOrderSqlParam param);
    public List<AttrKey> getAttrKeyListByCategory3Id(@Param("category3Id") Integer category3Id);

    @Select("select * from attr_key where attrKeyName=#{attrKeyName} and categoryId=#{categoryId} and categoryLevel=#{categoryLevel}")
    public AttrKey getAttrKeyByNameAndCategory(AttrKey attrKey);

    public Integer addAttrKey(AttrKey attrKey);

    public Integer updateAttrKeyName(AttrKey attrKey);

    @Delete("delete from attr_key where id = #{id}")
    public Integer deleteAttrKeyById(@Param("id") Integer id);

}
