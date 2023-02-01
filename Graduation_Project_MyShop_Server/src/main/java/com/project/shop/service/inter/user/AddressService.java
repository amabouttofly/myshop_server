package com.project.shop.service.inter.user;

import com.project.shop.pojo.po.user.UserAddress;
import com.project.shop.pojo.res.admin.AboutUserResponse;

public interface AddressService {

    public AboutUserResponse addUserAddress(UserAddress userAddress);
}
