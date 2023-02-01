package com.project.shop.dao.sku;

import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.sql.SaleAttrValueIdsSqlParam;
import com.project.shop.pojo.sql.SearchSkuListLimitPageWithOrderSqlParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SkuMapper {


    public Sku getSkuBySpuSaleAttrValueIds(SaleAttrValueIdsSqlParam param);
    public Integer getItemsTotalOfSkuListLimitPageWithOrder(SearchSkuListLimitPageWithOrderSqlParam param);
    public List<Sku> getSkuListLimitPageWithOrder(SearchSkuListLimitPageWithOrderSqlParam param);
    public Integer getCategory3IdByKeyWord(@Param("keyWord")String keyWord);

    @Select("select * from sku_list where spuId = #{spuId}")
    public List<Sku> getSkuListBySpuId(@Param("spuId") Integer spuId);

    @Select("select * from sku_list where id = #{skuId}")
    public Sku getSkuWithoutPropList(@Param("skuId")Integer skuId);
    @Select("select * from sku_list limit #{index},#{size}")
    public List<Sku> getSkuListLimitPage(@Param("index")Integer index,
                                         @Param("size")Integer size);

    @Select("select count(*) from sku_list")
    public Integer getSkuListItemsTotal();

    @Update("update sku_list set isSale = 1 where id = #{skuId}")
    public Integer updateSkuOnSale(@Param("skuId")Integer skuId);

    @Update("update sku_list set isSale = 0 where id = #{skuId}")
    public Integer updateSkuCancelSale(@Param("skuId")Integer skuId);

    public Sku getSkuBySkuId(@Param("skuId") Integer skuId);

    public Integer addSku(Sku sku);

    public Integer update(Sku sku);

    @Delete("delete from sku_list where id = #{skuId}")
    public Integer deleteSku(@Param("skuId")Integer skuId);
}
