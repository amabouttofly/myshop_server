package com.project.shop.service.inter.visitor;

import com.project.shop.pojo.bo.LimitPageWithOrderOfSkuBo;
import com.project.shop.pojo.bo.SkuDetailInfoBo;
import com.project.shop.pojo.req.product.SearchSkuRequest;

import java.util.List;

public interface SkuVisitorService {
    public Integer getCategory3IdByKeyWord(String keyWord);

    public LimitPageWithOrderOfSkuBo getLimitPageWithOrderBySearch(SearchSkuRequest searchSkuRequest);

    public SkuDetailInfoBo getSkuDetailInfo(Integer skuId);

    public SkuDetailInfoBo getSkuDetailInfoBySaleAttrValueIds(List<Integer> saleAttrValueIds);
}
