package com.project.shop.controller.visitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.bo.SkuDetailInfoBo;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.req.product.SkuDetailInfoRequest;
import com.project.shop.pojo.req.userAbout.SkuReviewListRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.base.CategoryService;
import com.project.shop.service.inter.user.ReplyService;
import com.project.shop.service.inter.user.ReviewService;
import com.project.shop.service.inter.visitor.SkuVisitorService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/product")
@RestController
public class VisitorController {

    private CategoryService categoryService;
    private SkuVisitorService skuVisitorService;

    private ReviewService reviewService;
    private ReplyService replyService;


    @GetMapping("/getCategoryList")
    public String getCategoryList() throws JsonProcessingException {
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData(categoryService.getAllLevelCategoryList());
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/searchSku")
    public String searchSku(@RequestBody SearchSkuRequest searchSkuRequest) throws JsonProcessingException {
        System.out.println("收到sku搜索请求,数据为:"+searchSkuRequest);
        AboutUserResponse response = new AboutUserResponse();
        Integer category3Id = null;
        if (searchSkuRequest.getKeyword() != null
                && !searchSkuRequest.getKeyword().trim().equals("")){
            System.out.println("进行keyWord匹配category3Id");
            category3Id = skuVisitorService.getCategory3IdByKeyWord(searchSkuRequest.getKeyword());
        }
        if (category3Id == null) {
            System.out.println("keyWord为空或者keyWord没有匹配上");
            category3Id = categoryService.getCategory3IdByAllLevel(searchSkuRequest);
        }
        System.out.println("查询到的category3Id为:"+category3Id);
        searchSkuRequest.setCategory3Id(category3Id);
        response.setData(skuVisitorService.getLimitPageWithOrderBySearch(searchSkuRequest));
        response.setCode(UserStaticData.PassCode);
        System.out.println("返回的相应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getSkuDetailInfo/{skuId}")
    public String getSkuDetailInfo(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
        System.out.println("收到获取sku细节详情请求,skuId为:"+skuId);
        SkuDetailInfoBo skuDetailInfoBo = skuVisitorService.getSkuDetailInfo(skuId);
        AboutUserResponse response = new AboutUserResponse();
        if (skuDetailInfoBo == null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("skuId不存在");
        } else {
            response.setCode(UserStaticData.PassCode);
            response.setData(skuDetailInfoBo);
        }
        System.out.println("回应的相应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/getSkuDetailInfo")
    public String getSkuDetailInfoBySaleAttrValueId(@RequestBody SkuDetailInfoRequest request) throws JsonProcessingException {
        // 如果请求携带的参数是对象时,必须要用post方式提交,这样编译时以二进制传输
        System.out.println("收到通过销售属性值id获取sku详情请求,数据为:"+ request);
        SkuDetailInfoBo skuDetailInfoBo = skuVisitorService.getSkuDetailInfoBySaleAttrValueIds(request.getSaleAttrValueIds());
        AboutUserResponse response = new AboutUserResponse();
        if (skuDetailInfoBo == null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("此sku不存在,或未上架");
        } else {
            response.setCode(UserStaticData.PassCode);
            response.setData(skuDetailInfoBo);
        }
        System.out.println("回应的相应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/getSkuReviewList")
    public String getSkuReviewList(@RequestBody SkuReviewListRequest request) throws JsonProcessingException {
        System.out.println("收到获取skuReview列表,请求数据:"+request);
        AboutUserResponse response = reviewService.getSkuReviewList(request);
        System.out.println("回应获取skuReview列表的相应数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getUserReplyListByReviewId/{reviewId}/{currentPage}/{pageLimit}")
    public String getUserReplyList(@PathVariable("reviewId")Integer reviewId,
                                   @PathVariable("currentPage")Integer currentPage,
                                   @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData(replyService.getUserReplyListByReviewId(currentPage,pageLimit,reviewId));
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setSkuVisitorService(SkuVisitorService skuVisitorService) {
        this.skuVisitorService = skuVisitorService;
    }
    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }
}
