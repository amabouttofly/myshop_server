package com.project.shop.dao.sku;

import com.project.shop.pojo.po.sku.Sku;
import org.apache.ibatis.annotations.Select;

public interface SkuSaleAttrValueMapper {

    @Select("select MAX(id) from sku_sale_attr_value")
    public Integer getMaxIdOfSkuSaleAttrValue();

    public Integer addSkuSaleAttrValueList(Sku sku);
}
