package com.project.shop.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.bo.EmailCodeBo;
import com.project.shop.pojo.req.userRegister.EmailRequest;
import com.project.shop.pojo.req.userRegister.SubmitUserInfoRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.mail.MailCodeService;
import com.project.shop.service.inter.role.UserService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/api/user/acl")
@RestController
public class UserRegisterController {
    private UserService userService;
    private MailCodeService mailCodeService;

    @PostMapping("/submitUserInfo")
    public String submitUserInfo(@RequestBody SubmitUserInfoRequest userInfoRequest) throws JsonProcessingException {
        System.out.println("收到注册消息提交请求,数据:"+userInfoRequest);
        EmailCodeBo emailCodeBo = mailCodeService.checkEmailCode(userInfoRequest.getToken());
        if (emailCodeBo == null) {
            AboutUserResponse response = new AboutUserResponse();
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("注册令牌不正确");
            return JsonUtils.getJsonString(response);
        }
        AboutUserResponse response = userService.submitUserInfo(userInfoRequest, emailCodeBo);
        System.out.println("回应注册提交消息的数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping(value = "/getEmailCode", produces = "application/json;charset=utf-8")
    public String getEmailCode(@RequestBody EmailRequest request) throws JsonProcessingException {
        System.out.println("收到邮箱为:"+request);
        AboutUserResponse response = new AboutUserResponse();
        User user = userService.queryUserByEmail(request.getEmail());
        if (user!=null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("该邮箱已经注册了");
            return JsonUtils.getJsonString(response);
        }

        String code = mailCodeService.createEmailCode();
        if (!mailCodeService.sendEmailCode(request.getEmail(),code)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("验证码发送失败");
        }
        EmailCodeBo emailCodeBo = new EmailCodeBo();
        emailCodeBo.setEmail(request.getEmail());
        emailCodeBo.setCode(code);
        String token = mailCodeService.createEmailValidateToken(emailCodeBo);
        response.setCode(UserStaticData.PassCode);
        response.setData(token);
        response.setMessage("验证码发送成功");

        System.out.println(response);
        return JsonUtils.getJsonString(response);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setMailCodeService(MailCodeService mailCodeService) {
        this.mailCodeService = mailCodeService;
    }
}
