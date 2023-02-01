package com.project.shop.service.inter.user;

import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.res.admin.AboutUserResponse;

import java.util.Map;

public interface ReplyService {

    public Map<String, Object> getUserReplyListByReviewId(Integer currentPage,Integer pageLimit, Integer reviewId);

    public AboutUserResponse submitUserReply(Reply reply);
}
