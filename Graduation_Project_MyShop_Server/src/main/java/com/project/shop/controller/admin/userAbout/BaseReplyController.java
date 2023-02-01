package com.project.shop.controller.admin.userAbout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.user.UserReplyConstant;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.base.BaseReplyService;
import com.project.shop.service.inter.user.ReplyService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/userAbout/baseReply")
@RestController
public class BaseReplyController {
    private BaseReplyService baseReplyService;

    @GetMapping("/getAllReplyStatus")
    public String getAllReplyStatus() throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        List<String> statusList = new ArrayList<>();
        statusList.add(UserReplyConstant.unapproved);
        statusList.add(UserReplyConstant.approved);
        statusList.add(UserReplyConstant.approvedFailed);
        response.setData(statusList);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getReplyListByStatus/{status}/{currentPage}/{pageLimit}")
    public String getReviewListByStatus(@PathVariable("status")String status,
                                        @PathVariable("currentPage")Integer currentPage,
                                        @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        System.out.println("收到回复列表请求,状态参数为:"+status);
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(baseReplyService.getReplyLimitListByStatus(status, currentPage, pageLimit));
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getUserReplyListByReviewId/{reviewId}/{currentPage}/{pageLimit}")
    public String getUserReplyList(@PathVariable("reviewId")Integer reviewId,
                                   @PathVariable("currentPage")Integer currentPage,
                                   @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(baseReplyService.getAllStatusReplyListByReviewId(currentPage,pageLimit,reviewId));
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/updateReplyStatus/{replyId}/{status}")
    public String updateReplyStatus(@PathVariable("replyId")Integer replyId,
                                     @PathVariable("status")String status) throws JsonProcessingException {
        System.out.println("更新回复状态请求: "+replyId+"-----"+status);
        AboutAdminResponse response = baseReplyService.updateReplyStatus(replyId, status);
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setBaseReplyService(BaseReplyService baseReplyService) {
        this.baseReplyService = baseReplyService;
    }
}
