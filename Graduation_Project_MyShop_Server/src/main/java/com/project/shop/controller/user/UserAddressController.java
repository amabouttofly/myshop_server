package com.project.shop.controller.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.user.UserAddress;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.role.User;
import com.project.shop.service.inter.user.AddressService;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/user/address")
@RestController
public class UserAddressController {

    private AddressService addressService;

    @PostMapping("/submitAddress")
    public String submitUserAddress(@RequestBody UserAddress userAddress,
                                    HttpServletRequest request) throws JsonProcessingException {
        System.out.println("用户地址添加请求:"+userAddress);

        String token = request.getHeader("X-Token");
        User user = JwtUtils.checkUserToken(token);
        userAddress.setUserId(user.getId());
        AboutUserResponse response = addressService.addUserAddress(userAddress);
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
}
