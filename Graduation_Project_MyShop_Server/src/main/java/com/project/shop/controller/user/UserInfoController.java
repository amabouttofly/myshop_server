package com.project.shop.controller.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.user.UserInfoConstant;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.role.UserService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/user/info")
@RestController
public class UserInfoController {
    private UserService userService;

    @GetMapping("/getAllDefaultAvatar")
    public String getAllDefaultAvatar() throws JsonProcessingException {
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData(UserInfoConstant.getAllDefaultAvatar());
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/updateUserAvatar")
    public String updateUserAvatar(@RequestBody User userAvatar, HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);

        user.setAvatar(userAvatar.getAvatar());

        AboutUserResponse response = new AboutUserResponse();
        if (userService.updateUserAvatar(user)) {
            response.setCode(UserStaticData.PassCode);
            response.setData(JwtUtils.createUserToken(user));
            response.setMessage("头像更新成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("头像更新失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
