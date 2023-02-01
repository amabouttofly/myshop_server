package com.project.shop.dao.spu;

import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.spu.Spu;
import com.project.shop.pojo.po.spu.SpuSaleAttrKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpuSaleAttrKeyMapper {

    public List<SpuSaleAttrKey> getSaleAttrKeyListBySku(Sku sku);

    public List<SpuSaleAttrKey> getSpuSaleAttrKeyListWithValueById(@Param("spuId")Integer spuId);

    public Integer addSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList);

    public Integer deleteSpuSaleAttrKeyListOfNoId(Spu spu);

    @Delete("delete from spu_sale_attr_key where spuId = #{id}")
    public Integer deleteSpuSaleAttrKeyListBySpuId(Spu spu);

    @Select("select MAX(id) from spu_sale_attr_key")
    public Integer getMaxIdOfSpuSaleAttrKey();

}
