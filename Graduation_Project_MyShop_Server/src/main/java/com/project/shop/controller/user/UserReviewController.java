package com.project.shop.controller.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.req.userAbout.SkuReviewListRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.base.SkuService;
import com.project.shop.service.inter.user.ReviewService;
import com.project.shop.service.inter.user.TradeService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/user/review")
@RestController
public class UserReviewController {

    private SkuService skuService;
    private ReviewService reviewService;

    private TradeService tradeService;

    @PostMapping("/submitReview/{skuId}/{orderDetailId}")
    public String submitReview(@PathVariable("skuId")Integer skuId,
                               @PathVariable("orderDetailId")Integer orderDetailId,
                               @RequestBody Review review, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到提交评论请求,评论:"+review);
        AboutUserResponse response = new AboutUserResponse();
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        review.setSkuId(skuId);
        review.setOrderDetailId(orderDetailId);
        review.setUserId(user.getId());
        review.setUserLevel(user.getLevel());
        review.setUserName(user.getName());
        review.setUserAvatar(user.getAvatar());
        response = reviewService.submitUserReview(review);
        System.out.println("返回的请求相应数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getReviewByOrderDetailId/{orderDetailId}")
    public String getReviewByOrderDetailId(@PathVariable("orderDetailId")Integer orderDetailId) throws JsonProcessingException {
        AboutUserResponse response = new AboutUserResponse();
        Review review = reviewService.getReviewByOrderDetailId(orderDetailId);
        if (review == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("该订单详情id没有评论");
        }else {
            response.setCode(UserStaticData.PassCode);
            response.setData(review);
        }
        System.out.println("回应通过订单详情id获取评论数据:"+response);
        return JsonUtils.getJsonString(response);
    }




    @Autowired
    public void setSkuService(SkuService skuService) {
        this.skuService = skuService;
    }
    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Autowired
    public void setTradeService(TradeService tradeService) {
        this.tradeService = tradeService;
    }
}
