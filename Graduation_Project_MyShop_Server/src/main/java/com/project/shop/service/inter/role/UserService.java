package com.project.shop.service.inter.role;

import com.project.shop.pojo.bo.EmailCodeBo;
import com.project.shop.pojo.req.userRegister.SubmitUserInfoRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;

public interface UserService {


    public Boolean updateUserAvatar(User user);

    public User queryUserByEmail(String email);

    public AboutUserResponse submitUserInfo(SubmitUserInfoRequest userInfoRequest, EmailCodeBo emailCodeBo);

    public User queryUserByUsernameAndPassword(String username,String password);
}
