package com.project.shop.service.impl.role;

import com.project.shop.dao.role.UserMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.user.UserInfoConstant;
import com.project.shop.pojo.bo.EmailCodeBo;
import com.project.shop.pojo.req.userRegister.SubmitUserInfoRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.role.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public Boolean updateUserAvatar(User user) {
        return userMapper.updateUserAvatar(user) == 1;
    }

    @Override
    public User queryUserByEmail(String email) {
        return userMapper.queryUserByEmail(email);
    }

    @Override
    public AboutUserResponse submitUserInfo(SubmitUserInfoRequest userInfoRequest, EmailCodeBo emailCodeBo) {
        AboutUserResponse response = new AboutUserResponse();

        if (userInfoRequest.getCode() == null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("验证码不能为空");
            return response;
        }

        if (!Objects.equals(emailCodeBo.getCode(), userInfoRequest.getCode())){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("验证码错误");
            return response;
        }
        User user = new User();
        user.setName(userInfoRequest.getName());
        user.setUsername(userInfoRequest.getUsername());
        user.setPassword(userInfoRequest.getPassword());

        if (user.getUsername() == null
                || user.getUsername().trim().equals("")
                || user.getPassword() == null
                || user.getPassword().trim().equals("")
                || user.getName() == null
                || user.getName().trim().equals("")) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("注册信息不完整");
            return response;
        }

        User checkUsername = userMapper.queryUserByUsername(user.getUsername());
        if (checkUsername != null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("账号重复");
            return response;
        }
        User checkName = userMapper.queryUserByName(user.getName());
        if (checkName != null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("昵称重复");
            return response;
        }

        if (userInfoRequest.getAvatar() == null
                || userInfoRequest.getAvatar().trim().equals("")){
            user.setAvatar(UserInfoConstant.defaultAvatar);
        }else {
            user.setAvatar(userInfoRequest.getAvatar());
        }
        user.setEmail(emailCodeBo.getEmail());
        user.setLevel(UserInfoConstant.defaultUserLevel);
        if (userMapper.addUser(user) == 1) {
            response.setCode(UserStaticData.PassCode);
            response.setMessage("注册成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("注册失败");
        }
        return response;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return userMapper.queryUserByUsernameAndPassword(username, password);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
