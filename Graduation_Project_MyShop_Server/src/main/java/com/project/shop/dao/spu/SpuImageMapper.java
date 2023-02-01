package com.project.shop.dao.spu;

import com.project.shop.pojo.po.spu.Spu;
import com.project.shop.pojo.po.spu.SpuImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpuImageMapper {

    @Select("select * from spu_image where spuId = #{spuId}")
    public List<SpuImage> getSpuImageListBySpuId(@Param("spuId") Integer spuId);

    public Integer deleteSpuImageBySpu(Spu spu);

    @Delete("delete from spu_image where spuId = #{spuId}")
    public Integer deleteSpuImageBySpuId(@Param("spuId") Integer spuId);

    public Integer addSpuImage(SpuImage spuImage);
}
