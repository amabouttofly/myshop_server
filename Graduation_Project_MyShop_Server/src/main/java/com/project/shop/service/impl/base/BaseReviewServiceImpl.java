package com.project.shop.service.impl.base;

import com.project.shop.dao.user.ReviewMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.user.UserReviewConstant;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.userAbout.UserReviewResponse;
import com.project.shop.service.inter.base.BaseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BaseReviewServiceImpl implements BaseReviewService {

    private ReviewMapper reviewMapper;

    @Override
    public Review getReviewByReviewId(Integer reviewId) {
        return reviewMapper.getReviewByReviewId(reviewId);
    }

    @Override
    public Map<String, Object> getReviewLimitListByStatus(String status, Integer currentPage, Integer pageLimit) {
        Integer itemsTotal = reviewMapper.getItemsTotalInStatus(status);
        if (itemsTotal==null) itemsTotal=0;
        if (pageLimit < 1) pageLimit = 1;
        if (currentPage < 1) currentPage = 1;

        while (pageLimit * (currentPage - 1) >= itemsTotal) {
            if(currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;
        List<Review> reviewList = reviewMapper.getReviewLimitListByStatus(status,index,size);

        // 将Date类型转换为日期格式字符串
        List<UserReviewResponse> reviewResponseList = new ArrayList<>();
        for (Review review: reviewList) {
            UserReviewResponse reviewResponse = new UserReviewResponse(review);
            reviewResponseList.add(reviewResponse);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal", itemsTotal);
        data.put("pagesTotal",pagesTotal);
        data.put("reviewList",reviewResponseList);
        return data;
    }

    @Override
    public AboutAdminResponse updateReviewStatus(Integer reviewId, String status) {
        List<String> statusList = UserReviewConstant.getReviewStatusList();
        AboutAdminResponse response = new AboutAdminResponse();
        if (!statusList.contains(status)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("请提交正确的评论状态");
            return response;
        }
        if (reviewMapper.updateReviewStatus(reviewId, status) == 1){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("评论状态更新至"+status);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("评论状态更新失败");
        }
        return response;
    }

    @Autowired
    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }
}
