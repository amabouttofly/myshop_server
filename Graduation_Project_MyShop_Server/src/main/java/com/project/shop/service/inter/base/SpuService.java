package com.project.shop.service.inter.base;

import com.project.shop.pojo.bo.LimitPageOfSpuBo;
import com.project.shop.pojo.po.spu.SaleAttrKey;
import com.project.shop.pojo.po.spu.Spu;
import com.project.shop.pojo.po.spu.SpuImage;
import com.project.shop.pojo.po.spu.SpuSaleAttrKey;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SpuService {

    public LimitPageOfSpuBo getSpuListLimitPage(Integer currentPage, Integer pageLimit, Integer category3Id);

    public Spu getSpuById(Integer spuId);

    public List<SaleAttrKey> getSaleAttrKeyList();

    public Spu getSpuBySpuName(String spuName);

    public AboutAdminResponse saveSpuInfo(Spu spu);

    public AboutAdminResponse SpuImageFileUpload(String spuName,Integer category3Id,Integer spuId, MultipartFile file, HttpServletRequest request);

    public Integer deleteSpuById(Integer spuId);

    public List<SpuImage> getSpuImageListBySpuId(Integer spuId);

    public List<SpuSaleAttrKey> getSpuSaleAttrList(Integer spuId);
}
