package com.project.shop.service.impl.base;

import com.project.shop.dao.user.ReplyMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.user.UserReplyConstant;
import com.project.shop.global.user.UserReviewConstant;
import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.userAbout.UserReplyResponse;
import com.project.shop.pojo.res.userAbout.UserReviewResponse;
import com.project.shop.service.inter.base.BaseReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BaseReplyServiceImpl implements BaseReplyService {
    private ReplyMapper replyMapper;

    @Override
    public Map<String, Object> getAllStatusReplyListByReviewId(Integer currentPage, Integer pageLimit, Integer reviewId) {
        Integer itemsTotal = replyMapper.getItemsTotalByReviewId(reviewId);

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

        List<Reply> replyList = replyMapper.getReplyLimitListByReviewId(reviewId,index,size);
        List<UserReplyResponse> replyResponseList = new ArrayList<>();
        for (Reply reply : replyList) {
            UserReplyResponse replyResponse = new UserReplyResponse(reply);
            replyResponseList.add(replyResponse);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal", itemsTotal);
        data.put("pagesTotal",pagesTotal);
        data.put("replyList",replyResponseList);

        return data;
    }

    @Override
    public Map<String, Object> getReplyLimitListByStatus(String status, Integer currentPage, Integer pageLimit){
        Integer itemsTotal = replyMapper.getItemsTotalInStatus(status);
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
        List<Reply> replyList = replyMapper.getReplyLimitListByStatus(status,index,size);

        // 将Date类型转换为日期格式字符串
        List<UserReplyResponse> replyResponseList = new ArrayList<>();
        for (Reply reply: replyList) {
            UserReplyResponse replyResponse = new UserReplyResponse(reply);
            replyResponseList.add(replyResponse);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal", itemsTotal);
        data.put("pagesTotal",pagesTotal);
        data.put("replyList",replyResponseList);
        return data;
    }

    @Override
    public AboutAdminResponse updateReplyStatus(Integer replyId, String status) {
        List<String> statusList = UserReplyConstant.getReplyStatusList();
        AboutAdminResponse response = new AboutAdminResponse();
        if (!statusList.contains(status)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("请提交正确的回复状态");
            return response;
        }
        if (replyMapper.updateReplyStatus(replyId, status) == 1){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("回复状态更新至"+status);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("回复状态更新失败");
        }
        return response;
    }

    @Autowired
    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }
}
