package com.project.shop.dao.role;

import com.project.shop.pojo.role.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("select * from admin where username=#{username} and password=#{password}")
    public Admin queryAdminByUsername(@Param("username")String username,@Param("password")String password);

}
