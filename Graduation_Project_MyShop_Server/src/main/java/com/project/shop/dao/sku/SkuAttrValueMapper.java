package com.project.shop.dao.sku;

import com.project.shop.pojo.po.sku.Sku;
import org.apache.ibatis.annotations.Select;

public interface SkuAttrValueMapper {

    @Select("select MAX(id) from sku_attr_value")
    public Integer getMaxIdOfSkuAttrValue();

    public Integer addSkuAttrValueMapper(Sku sku);
}
