package com.project.shop.service.impl.base;

import com.project.shop.dao.sku.SkuAttrValueMapper;
import com.project.shop.dao.sku.SkuImageMapper;
import com.project.shop.dao.sku.SkuMapper;
import com.project.shop.dao.sku.SkuSaleAttrValueMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.bo.LimitPageOfSkuBo;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.sku.SkuAttrValue;
import com.project.shop.pojo.po.sku.SkuImage;
import com.project.shop.pojo.po.sku.SkuSaleAttrValue;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.sql.SaleAttrValueIdsSqlParam;
import com.project.shop.service.inter.base.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SkuServiceImpl implements SkuService {

    private SkuMapper skuMapper;
    private SkuImageMapper skuImageMapper;
    private SkuAttrValueMapper skuAttrValueMapper;
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;




    @Override
    public Boolean onSale(Integer skuId) {
        return skuMapper.updateSkuOnSale(skuId) == 1;
    }

    @Override
    public Boolean cancelSale(Integer skuId) {
        return skuMapper.updateSkuCancelSale(skuId) == 1;
    }

    @Override
    public Sku getSkuBySkuId(Integer skuId) {
        return skuMapper.getSkuBySkuId(skuId);
    }

    @Override
    public Sku getSkuWithoutPropList(Integer skuId) {
        return skuMapper.getSkuWithoutPropList(skuId);
    }

    @Override
    public Boolean deleteSku(Integer skuId) {
        return skuMapper.deleteSku(skuId) == 1;
    }

    @Override
    public LimitPageOfSkuBo getSkuListLimitPage(Integer currentPage, Integer pageLimit) {
        Integer itemsTotal = skuMapper.getSkuListItemsTotal();
        if (itemsTotal == null) itemsTotal = 0;
        if (currentPage < 1) currentPage = 1;
        if (pageLimit < 1) pageLimit = 1;
        while (pageLimit * (currentPage - 1) >= itemsTotal) {
            if(currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        LimitPageOfSkuBo limitPageOfSkuBo = new LimitPageOfSkuBo();
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;
        limitPageOfSkuBo.setSkuList(skuMapper.getSkuListLimitPage(index, size));
        limitPageOfSkuBo.setCurrentPage(currentPage);
        limitPageOfSkuBo.setPageLimit(pageLimit);
        limitPageOfSkuBo.setItemsTotal(itemsTotal);
        limitPageOfSkuBo.setPagesTotal(pagesTotal);
        return limitPageOfSkuBo;
    }

    @Override
    public List<Sku> getSkuListBySpuId(Integer spuId) {
        return skuMapper.getSkuListBySpuId(spuId);
    }

    @Override
    public AboutAdminResponse saveSku(Sku sku) {
        AboutAdminResponse response = null;
        if (!checkSkuInfo(sku)) {
            response = new AboutAdminResponse();
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("sku信息存在错误");
            return response;
        }
        if (sku.getId() == null) {
            System.out.println("执行sku插入操作");
            response = addSku(sku);
        }else {
            System.out.println("执行sku更新操作");
            response = updateSku(sku);
        }
        return response;
    }

    public AboutAdminResponse updateSku(Sku sku) {
        AboutAdminResponse response = new AboutAdminResponse();
        return response;
    }

    public AboutAdminResponse addSku(Sku sku) {
        AboutAdminResponse response = new AboutAdminResponse();

        if (sku.getSkuSaleAttrValueList() != null && sku.getSkuSaleAttrValueList().size() > 0){
            SaleAttrValueIdsSqlParam param = new SaleAttrValueIdsSqlParam();
            List<Integer> saleAttrValueIds = new ArrayList<>();
            for (SkuSaleAttrValue skuSaleAttrValue : sku.getSkuSaleAttrValueList()) {
                saleAttrValueIds.add(skuSaleAttrValue.getSaleAttrValueId());
            }
            param.setSaleAttrValueIds(saleAttrValueIds);
            param.setSaleAttrValueIdsSize(saleAttrValueIds.size());
            System.out.println("查询参数为:"+param);
            if (skuMapper.getSkuBySpuSaleAttrValueIds(param) != null) {
                response.setCode(ResponseCodeConstant.ServiceFailedCode);
                response.setMessage("已存在拥有当前销售属性值的SKU");
                return response;
            }
        }

        if (skuMapper.addSku(sku) < 1){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("SKU插入失败");
            return response;
        }
        Integer addSkuImageNum = addSkuImageList(sku);
        System.out.println("sku图片插入的数量为:"+addSkuImageNum);
        Integer addSkuAttrValueNum = addSkuAttrValueList(sku);
        System.out.println("sku属性值插入数量为:"+addSkuAttrValueNum);
        Integer addSkuSaleAttrValueNum = addSkuSaleAttrValueList(sku);
        System.out.println("SKU销售属性插入数量为:"+addSkuSaleAttrValueNum);
        response.setCode(AdminStaticData.PassCode);
        response.setMessage("SKU保存成功");
        return response;
    }

    public Boolean checkSkuInfo(Sku sku) {
        if (sku.getCategory3Id() == null
                || sku.getTmId() == null
                || sku.getSpuId() == null
                || sku.getSkuName() == null
                || sku.getSkuName().trim().equals("")
                || sku.getSkuDesc() == null
                || sku.getSkuDesc().trim().equals("")) {
            return false;
        }
        return true;
    }

    public Integer addSkuImageList(Sku sku) {
        if (sku.getSkuImageList() == null || sku.getSkuImageList().size() < 1) {
            return 0;
        }
        Integer maxId = skuImageMapper.getMaxIdOfSkuImage();
        if (maxId == null) {
            maxId = 0;
        }
        for (SkuImage skuImage: sku.getSkuImageList()) {
            maxId++;
            skuImage.setId(maxId);
        }
        return skuImageMapper.addSkuImageList(sku);
    }
    public Integer addSkuAttrValueList(Sku sku){
        if (sku.getSkuAttrValueList() == null || sku.getSkuAttrValueList().size() < 1) {
            return 0;
        }
        Integer maxId = skuAttrValueMapper.getMaxIdOfSkuAttrValue();
        if (maxId == null) {
            maxId = 0;
        }
        for (SkuAttrValue skuAttrValue : sku.getSkuAttrValueList()) {
            maxId++;
            skuAttrValue.setId(maxId);
        }
        return skuAttrValueMapper.addSkuAttrValueMapper(sku);
    }
    public Integer addSkuSaleAttrValueList(Sku sku) {
        if (sku.getSkuSaleAttrValueList() == null || sku.getSkuSaleAttrValueList().size() < 1) {
            return 0;
        }
        Integer maxId = skuSaleAttrValueMapper.getMaxIdOfSkuSaleAttrValue();
        if (maxId == null) {
            maxId = 0;
        }
        for (SkuSaleAttrValue skuSaleAttrValue: sku.getSkuSaleAttrValueList()) {
            maxId++;
            skuSaleAttrValue.setId(maxId);
        }
        return skuSaleAttrValueMapper.addSkuSaleAttrValueList(sku);
    }


    @Autowired
    public void setSkuMapper(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }
    @Autowired
    public void setSkuImageMapper(SkuImageMapper skuImageMapper) {
        this.skuImageMapper = skuImageMapper;
    }
    @Autowired
    public void setSkuAttrValueMapper(SkuAttrValueMapper skuAttrValueMapper) {
        this.skuAttrValueMapper = skuAttrValueMapper;
    }
    @Autowired
    public void setSkuSaleAttrValueMapper(SkuSaleAttrValueMapper skuSaleAttrValueMapper) {
        this.skuSaleAttrValueMapper = skuSaleAttrValueMapper;
    }
}
