package com.project.shop.controller.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.res.admin.login.UserLoginResData;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.role.UserService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserLoginController {

    private UserService userService;

    @PostMapping("/acl/login")
    public String userLogin(@RequestBody User user) throws JsonProcessingException {
        System.out.println("==========user--login========");
        System.out.println(user.getUsername()+"---------"+user.getPassword());
        AboutUserResponse response = new AboutUserResponse();
        User checkUser = userService.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if ( checkUser != null){
            System.out.println("账号正确");
            UserLoginResData data = new UserLoginResData();
            data.setToken(JwtUtils.createUserToken(checkUser));
            response.setCode(UserStaticData.PassCode);
            response.setData(data);
        }else {
            System.out.println("账号错误");
            response.setCode(UserStaticData.LoginFalseCode);
            response.setMessage("用户名或密码不正确");
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/acl/info")
    public String userInfo(String token) throws JsonProcessingException {
        System.out.println("==========user--info========");
        System.out.println("token:"+token);
        AboutUserResponse response = new AboutUserResponse();
        User user = JwtUtils.checkUserToken(token);
        if (user!=null){
            System.out.println("--token正确,验证成功--");
            response.setCode(UserStaticData.PassCode);
            response.setData(user);
        }else {
            System.out.println("--token错误,验证失败--");
            response.setCode(UserStaticData.TokenFalseCode);
            response.setMessage("token无效,无法获取登录信息");

        }
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/acl/logout")
    public String userLogout() throws JsonProcessingException {
        System.out.println("==========logout========");
        AboutUserResponse response = new AboutUserResponse();
        response.setCode(UserStaticData.PassCode);
        response.setData("退出登录成功");
        return JsonUtils.getJsonString(response);
    }




    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
