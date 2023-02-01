package com.project.shop.controller.admin.login;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.pojo.role.Admin;
import com.project.shop.pojo.req.admin.login.AdminLoginRequest;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.login.AdminLoginResData;
import com.project.shop.service.inter.role.AdminService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class AdminLoginController {

//    private static final Integer passCode=20000;
//    private static final Integer loginFalseCode=60204;
//    private static final Integer tokenFalseCode=50008;
    private AdminService adminService;

    @PostMapping("/acl/login")
    public String adminLogin(@RequestBody AdminLoginRequest adminLoginRequest) throws JsonProcessingException {
        System.out.println("==========login========");
        System.out.println(adminLoginRequest.getUsername()+"---------"+adminLoginRequest.getPassword());
        AboutAdminResponse response=new AboutAdminResponse();
        Admin admin=adminService.queryAdminByUsername(adminLoginRequest.getUsername(),adminLoginRequest.getPassword());
        if (admin!=null){
            System.out.println("账号正确");
            //正确则添加code,并给data的token属性加入token
            AdminLoginResData data=new AdminLoginResData();
            data.setToken(JwtUtils.createAdminToken(admin));
            response.setCode(AdminStaticData.PassCode);
            response.setData(data);
        }else {
            System.out.println("账号错误");
            response.setCode(AdminStaticData.LoginFalseCode);
            response.setMessage("用户名或密码不正确");
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/acl/info")
    public String adminInfo(String token) throws JsonProcessingException {
        System.out.println("==========info========");
        System.out.println("token:"+token);
        AboutAdminResponse response=new AboutAdminResponse();
        Admin admin=JwtUtils.checkAdminToken(token);
        if (admin!=null){
            System.out.println("--token正确,验证成功--");
            response.setCode(AdminStaticData.PassCode);
            response.setData(admin);
        }else {
            System.out.println("--token错误,验证失败--");
            response.setCode(AdminStaticData.TokenFalseCode);
            response.setMessage("token无效,无法获取登录信息");

        }
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/acl/logout")
    public String adminLogout() throws JsonProcessingException {
        System.out.println("==========logout========");
        AboutAdminResponse response=new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData("退出登录成功");
        return JsonUtils.getJsonString(response);
    }



    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
