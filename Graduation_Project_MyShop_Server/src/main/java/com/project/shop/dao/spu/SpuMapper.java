package com.project.shop.dao.spu;

import com.project.shop.pojo.po.spu.SaleAttrKey;
import com.project.shop.pojo.po.spu.Spu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpuMapper {

    @Select("select * from sale_attr_key")
    public List<SaleAttrKey> getSaleAttrKeyList();

    @Select("select * " +
            "from spu_list " +
            "where category3Id = #{category3Id} " +
            "limit #{index},#{size}")
    public List<Spu> getSpuListLimitPageByCategory3Id(@Param("index")Integer index,
                                         @Param("size")Integer size,
                                         @Param("category3Id") Integer category3Id);

    @Select("select count(*) from spu_list where category3Id = #{category3Id}")
    public Integer getSpuListItemTotalByCategory3Id(@Param("category3Id") Integer category3Id);

    public Spu getSpuById(@Param("spuId")Integer spuId);


    @Select("select * from spu_list where spuName = #{spuName}")
    public Spu getSpuBySpuName(@Param("spuName") String spuName);

    @Select("select * from spu_list where spuName = #{spuName} and category3Id = #{category3Id}")
    public Spu getSpuBySpuNameAndCategory3Id(Spu spu);

    public Integer updateSpuById(Spu spu);

    public Integer addSpu(Spu spu);

    @Delete("delete from spu_list where id = #{spuId}")
    public Integer deleteSpuById(@Param("spuId") Integer spuId);
}
