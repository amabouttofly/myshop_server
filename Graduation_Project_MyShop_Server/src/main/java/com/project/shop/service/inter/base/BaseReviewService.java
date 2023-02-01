package com.project.shop.service.inter.base;

import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.res.admin.AboutAdminResponse;

import java.util.List;
import java.util.Map;

public interface BaseReviewService {

    public Review getReviewByReviewId(Integer reviewId);

    public Map<String,Object> getReviewLimitListByStatus(String status, Integer currentPage, Integer pageLimit);

    public AboutAdminResponse updateReviewStatus(Integer reviewId, String status);

}
