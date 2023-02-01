package com.project.shop.service.impl.visitor;

import com.project.shop.dao.attr.AttrMapper;
import com.project.shop.dao.attr.CategoryMapper;
import com.project.shop.dao.attr.TrademarkMapper;
import com.project.shop.dao.sku.SkuMapper;
import com.project.shop.dao.spu.SpuSaleAttrKeyMapper;
import com.project.shop.pojo.bo.LimitPageWithOrderOfSkuBo;
import com.project.shop.pojo.bo.SkuDetailInfoBo;
import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.po.spu.SpuSaleAttrKey;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.sql.CategoryViewSqlResult;
import com.project.shop.pojo.sql.SaleAttrValueIdsSqlParam;
import com.project.shop.pojo.sql.SearchSkuListLimitPageWithOrderSqlParam;
import com.project.shop.service.inter.visitor.SkuVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service
public class SkuVisitorServiceImpl implements SkuVisitorService {


    private SkuMapper skuMapper;
    private TrademarkMapper trademarkMapper;
    private AttrMapper attrMapper;
    private CategoryMapper categoryMapper;

    private SpuSaleAttrKeyMapper spuSaleAttrKeyMapper;

    @Override
    public LimitPageWithOrderOfSkuBo getLimitPageWithOrderBySearch(SearchSkuRequest request) {
        LimitPageWithOrderOfSkuBo limitPage = new LimitPageWithOrderOfSkuBo();

        SearchSkuListLimitPageWithOrderSqlParam param = new SearchSkuListLimitPageWithOrderSqlParam();
        // category3Id不能为空
        param.setCategory3Id(request.getCategory3Id());
        if (request.getTrademark() != null) {
            param.setTmId(request.getTrademark().getId());
        }
        if (request.getSkuAttrValueList() != null && request.getSkuAttrValueList().size() > 0){
            param.setSkuAttrValueList(request.getSkuAttrValueList());
            param.setSkuAttrValueNum(request.getSkuAttrValueList().size());
        }
        // 此mapper需要category3Id,tmId, skuAttrValueList和skuAttrValueList的大小,后三个可为null
        System.out.println("开始查询sku结果数量");
        Integer itemsTotal = skuMapper.getItemsTotalOfSkuListLimitPageWithOrder(param);

        Integer currentPage = request.getCurrentPage();
        Integer pageLimit = request.getPageLimit();
        if (currentPage < 1) currentPage = 1;
        if (pageLimit < 1) pageLimit = 1;
        if (itemsTotal == null) itemsTotal = 0;
        while ((currentPage - 1) * pageLimit >= itemsTotal) {
            if (currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        // 1.装配分页信息
        limitPage.setCurrentPage(currentPage);
        limitPage.setPageLimit(pageLimit);
        limitPage.setPagesTotal(pagesTotal);
        limitPage.setItemsTotal(itemsTotal);


        param.setIndex((currentPage - 1) * pageLimit);
        param.setSize(pageLimit);
        // order: 1代表升序asc,2代表降序desc
        if (request.getOrder() != null) {
            // null不能比较大小
            if (request.getOrder() == 1 || request.getOrder() ==2 ) {
                param.setOrder(request.getOrder());
            }
            if (Objects.equals(request.getOrderOf(), "price")){
                param.setOrderOf(request.getOrderOf());
            }
        }
        System.out.println("查询前的param参数:"+param);
        List<Sku> skuList = skuMapper.getSkuListLimitPageWithOrder(param);
        System.out.println("查询结果:"+skuList);
        // 2.装配skuList信息
        limitPage.setSkuList(skuList);

        // 3.装配商标列表信息
        if (skuList != null && skuList.size()>0) {
            // 从得到的skuList中,取出所有的商标信息,如果skuList得到为空,则无商标返回
            System.out.println("查询出sku列表");
            if (param.getTmId() == null) {
                System.out.println("查询商标列表");
                limitPage.setTrademarkList(trademarkMapper.getTrademarkListInSkuList(skuList));
            }
        }

        // 4.装配平台属性信息
        // 无论得到的skuList是否为空,都进行查询
        // 如果请求中的skuAttrValueList为空,则寻找category3Id下的所有平台属性
        // 如果请求中的skuAttrValueList不为空,则在当前category3Id下的所有平台属性的基础上,过滤掉存在于skuAttrValueList中的key
        List<AttrKey> attrKeyList = attrMapper.getAttrKeyListInSkuList(param);
        limitPage.setAttrKeyList(attrKeyList);
        return limitPage;
    }

    @Override
    public Integer getCategory3IdByKeyWord(String keyWord) {
        return skuMapper.getCategory3IdByKeyWord(keyWord);
    }

    @Override
    public SkuDetailInfoBo getSkuDetailInfo(Integer skuId) {
        Sku sku = skuMapper.getSkuBySkuId(skuId);
        if (sku == null) {
            return null;
        }
        SkuDetailInfoBo skuDetailInfoBo = new SkuDetailInfoBo();
        // 1.装配sku
        skuDetailInfoBo.setSku(sku);

        CategoryViewSqlResult categoryView = categoryMapper.getCategoryView(sku.getCategory3Id());
        // 2.装配categoryView
        skuDetailInfoBo.setCategoryView(categoryView);

        List<SpuSaleAttrKey> spuSaleAttrKeyList = spuSaleAttrKeyMapper.getSaleAttrKeyListBySku(sku);
        // 3.装配spuSaleAttrKeyList
        skuDetailInfoBo.setSpuSaleAttrKeyList(spuSaleAttrKeyList);
        return skuDetailInfoBo;
    }
    @Override
    public SkuDetailInfoBo getSkuDetailInfoBySaleAttrValueIds(List<Integer> saleAttrValueIds) {
        if (saleAttrValueIds == null || saleAttrValueIds.size() == 0) {
            return null;
        }
        SaleAttrValueIdsSqlParam param = new SaleAttrValueIdsSqlParam();
        param.setSaleAttrValueIds(saleAttrValueIds);
        param.setSaleAttrValueIdsSize(saleAttrValueIds.size());

        Sku sku = skuMapper.getSkuBySpuSaleAttrValueIds(param);
        // skuMapper管理员模块也在使用,因此不能加上 isSale = 1判断
        if (sku == null || sku.getIsSale()==null || sku.getIsSale() != 1) {
            return null;
        }
        return getSkuDetailInfo(sku.getId());
    }

    @Autowired
    public void setSkuMapper(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }
    @Autowired
    public void setTrademarkMapper(TrademarkMapper trademarkMapper) {
        this.trademarkMapper = trademarkMapper;
    }
    @Autowired
    public void setAttrMapper(AttrMapper attrMapper) {
        this.attrMapper = attrMapper;
    }
    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    @Autowired
    public void setSpuSaleAttrKeyMapper(SpuSaleAttrKeyMapper spuSaleAttrKeyMapper) {
        this.spuSaleAttrKeyMapper = spuSaleAttrKeyMapper;
    }
}
