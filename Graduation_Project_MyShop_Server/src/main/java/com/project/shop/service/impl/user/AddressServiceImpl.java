package com.project.shop.service.impl.user;

import com.project.shop.dao.user.UserAddressMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.pojo.po.user.UserAddress;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AddressServiceImpl implements AddressService {
    private UserAddressMapper userAddressMapper;

    @Override
    public AboutUserResponse addUserAddress(UserAddress userAddress) {
        AboutUserResponse response = new AboutUserResponse();

        if (userAddress.getUserAddress() == null
                || userAddress.getUserAddress().trim().equals("")
                || userAddress.getConsignee() == null
                || userAddress.getConsignee().trim().equals("")
                || userAddress.getPhone() == null
                || userAddress.getPhone().trim().equals("")){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("地址信息不完整");
            return response;
        }

        if (userAddressMapper.addUserAddress(userAddress) == 1){
            response.setCode(UserStaticData.PassCode);
            response.setMessage("地址添加成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("地址添加失败");
        }
        return response;
    }

    @Autowired
    public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }
}
