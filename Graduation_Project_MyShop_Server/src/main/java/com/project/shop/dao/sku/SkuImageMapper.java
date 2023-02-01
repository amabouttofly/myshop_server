package com.project.shop.dao.sku;

import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.sku.SkuImage;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SkuImageMapper {

    @Select("select MAX(id) from sku_image")
    public Integer getMaxIdOfSkuImage();
    public Integer addSkuImageList(Sku sku);
}
