package com.project.shop.dao.spu;

import com.project.shop.pojo.po.spu.SpuSaleAttrKey;
import com.project.shop.pojo.po.spu.SpuSaleAttrValue;
import com.project.shop.pojo.sql.DeleteSpuSaleAttrValueSqlParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpuSaleAttrValueMapper {

    public Integer addSpuSaleAttrValueFormSpuSaleKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList);

    public Integer addSpuSaleAttrValueList(List<SpuSaleAttrValue> spuSaleAttrValueList);

    public Integer deleteSaleAttrValueListByIdNotInList(DeleteSpuSaleAttrValueSqlParam deleteSpuSaleAttrValueSqlParam);

    public Integer deleteSaleAttrValueBySpuSaleAttrKeyIdList(List<Integer> spuSaleAttrKeyIdList);

    @Select("select MAX(id) from spu_sale_attr_value")
    public Integer getMaxIdOfSpuSaleAttrValue();
}
