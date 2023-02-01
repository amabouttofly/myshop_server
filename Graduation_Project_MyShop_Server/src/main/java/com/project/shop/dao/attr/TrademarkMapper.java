package com.project.shop.dao.attr;

import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.po.sku.Sku;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TrademarkMapper {

    public List<Trademark> getTrademarkListInSkuList(List<Sku> skuList);
    public List<Trademark> getLimitPage(@Param("index")Integer index,@Param("size")Integer size);

    public Integer addTrademarkWithoutId(Trademark trademark);

    public Integer updateTrademarkById(Trademark trademark);

    @Select("select * from trademark")
    public List<Trademark> getTrademarkList();

    @Select("select count(*) from trademark")
    public Integer getItemsTotal();

    @Select("select * from trademark where tmName=#{tmName}")
    public Trademark getTrademarkByTmName(@Param("tmName")String tmName);

    @Select("select * from trademark where id=#{id}")
    public Trademark getTrademarkById(@Param("id") Integer id);

    @Delete("delete from trademark where id=#{id}")
    public Integer deleteTrademarkById(@Param("id") Integer id);

}
