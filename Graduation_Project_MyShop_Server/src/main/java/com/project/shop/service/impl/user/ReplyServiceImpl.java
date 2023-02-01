package com.project.shop.service.impl.user;

import com.project.shop.dao.user.ReplyMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.user.UserReplyConstant;
import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.res.userAbout.UserReplyResponse;
import com.project.shop.pojo.res.userAbout.UserReviewResponse;
import com.project.shop.service.inter.user.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ReplyServiceImpl implements ReplyService {
    private ReplyMapper replyMapper;

    @Override
    public Map<String, Object> getUserReplyListByReviewId(Integer currentPage, Integer pageLimit, Integer reviewId) {
        Integer itemsTotal = replyMapper.getItemsTotalByReviewIdAndStatus(reviewId, UserReplyConstant.approved);

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

        List<Reply> replyList = replyMapper.getReplyLimitListByReviewIdAndStatus(reviewId,UserReplyConstant.approved,index,size);
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
    public AboutUserResponse submitUserReply(Reply reply) {
        AboutUserResponse response = new AboutUserResponse();
        if (reply.getReviewId() == null
                || reply.getReply() == null
                || reply.getReply().trim().equals("")){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("回复参数不完整");
            return response;
        }
        if (reply.getReplyUserName() != null && reply.getReplyUserName().trim().equals("")) {
            reply.setReplyUserName(null);
        }
        if (
                (reply.getReplyId() == null
                        || reply.getReplyUserId() == null
                        || reply.getReplyUserName() == null)
                && !(reply.getReplyId() == null
                        && reply.getReplyUserId() == null
                        && reply.getReplyUserName() == null)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("replyId,replyUserId,replyUserName要么都空,要么都不空");
            return response;
        }

        reply.setStatus(UserReplyConstant.unapproved);
        reply.setReplyTime(new Date());
        if (replyMapper.addReply(reply) == 1){
            response.setCode(UserStaticData.PassCode);
            response.setMessage("回复添加成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("回复添加失败");
        }

        return response;
    }


    @Autowired
    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }
}
