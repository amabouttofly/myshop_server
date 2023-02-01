package com.project.shop.service.inter.base;

import com.project.shop.pojo.res.admin.AboutAdminResponse;

import java.util.Map;

public interface BaseReplyService {

    public Map<String, Object> getAllStatusReplyListByReviewId(Integer currentPage, Integer pageLimit, Integer reviewId);

    public Map<String, Object> getReplyLimitListByStatus(String status, Integer currentPage, Integer pageLimit);

    public AboutAdminResponse updateReplyStatus(Integer replyId, String status);
}
