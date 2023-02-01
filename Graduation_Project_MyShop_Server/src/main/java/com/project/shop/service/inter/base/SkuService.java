package com.project.shop.service.inter.base;

import com.project.shop.pojo.bo.LimitPageOfSkuBo;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuService {

    public LimitPageOfSkuBo getSkuListLimitPage(Integer currentPage, Integer pageLimit);
    public AboutAdminResponse saveSku(Sku sku);

    public List<Sku> getSkuListBySpuId(Integer spuId);

    public Sku getSkuBySkuId(Integer skuId);

    public Sku getSkuWithoutPropList(Integer skuId);

    public Boolean onSale(Integer skuId);
    public Boolean cancelSale(Integer skuId);

    public Boolean deleteSku(Integer skuId);
}
