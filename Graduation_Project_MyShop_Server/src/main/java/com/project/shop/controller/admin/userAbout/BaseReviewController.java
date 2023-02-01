package com.project.shop.controller.admin.userAbout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.user.UserReviewConstant;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.base.BaseReviewService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/userAbout/baseReview")
@RestController
public class BaseReviewController {
    private BaseReviewService baseReviewService;

    @GetMapping("/getAllReviewStatus")
    public String getAllReviewStatus() throws JsonProcessingException {
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        List<String> statusList = new ArrayList<>();
        statusList.add(UserReviewConstant.unapproved);
        statusList.add(UserReviewConstant.approved);
        statusList.add(UserReviewConstant.approvedFailed);
        response.setData(statusList);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getReviewListByStatus/{status}/{currentPage}/{pageLimit}")
    public String getReviewListByStatus(@PathVariable("status")String status,
                                        @PathVariable("currentPage")Integer currentPage,
                                        @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        System.out.println("收到评论列表请求,状态参数为:"+status);
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData(baseReviewService.getReviewLimitListByStatus(status, currentPage, pageLimit));
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/updateReviewStatus/{reviewId}/{status}")
    public String updateReviewStatus(@PathVariable("reviewId")Integer reviewId,
                                     @PathVariable("status")String status) throws JsonProcessingException {
        System.out.println("更新评论状态请求: "+reviewId+"-----"+status);
        AboutAdminResponse response = baseReviewService.updateReviewStatus(reviewId, status);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getReviewByReviewId/{reviewId}")
    public String getReviewByReviewId(@PathVariable("reviewId")Integer reviewId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(baseReviewService.getReviewByReviewId(reviewId));
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setBaseReviewService(BaseReviewService baseReviewService) {
        this.baseReviewService = baseReviewService;
    }
}
