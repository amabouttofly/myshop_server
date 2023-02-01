package com.project.shop.dao.user;

import com.project.shop.pojo.po.user.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAddressMapper {


    public Integer addUserAddress(UserAddress userAddress);

    @Select("select * from user_address where userId = #{userId}")
    public List<UserAddress> getUserAddressListByUserId(@Param("userId") Integer userId);
}
