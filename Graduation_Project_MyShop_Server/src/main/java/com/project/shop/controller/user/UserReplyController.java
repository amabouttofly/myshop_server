package com.project.shop.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.user.ReplyService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/user/reply")
@RestController
public class UserReplyController {
    private ReplyService replyService;

    @PostMapping("/submitReply")
    public String submitReply(@RequestBody Reply reply, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到添加回复请求==="+reply);
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        reply.setUserId(user.getId());
        reply.setUserLevel(user.getLevel());
        reply.setUserAvatar(user.getAvatar());
        reply.setUserName(user.getName());

        AboutUserResponse response = replyService.submitUserReply(reply);
        System.out.println("回应添加回复的相应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }


    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }
}
