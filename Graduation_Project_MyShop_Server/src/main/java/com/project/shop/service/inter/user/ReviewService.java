package com.project.shop.service.inter.user;

import com.project.shop.pojo.po.trade.OrderDetail;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.req.userAbout.SkuReviewListRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;

public interface ReviewService {

    public AboutUserResponse getSkuReviewList(SkuReviewListRequest request);

    public Review getReviewByOrderDetailId(Integer orderDetailId);
    public AboutUserResponse submitUserReview(Review review);
}
