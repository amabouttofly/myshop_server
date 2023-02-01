package com.project.shop.dao.role;

import com.project.shop.pojo.role.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select * from user_list where email=#{email}")
    public User queryUserByEmail(@Param("email") String email);

    @Select("select * from user_list where name=#{name}")
    public User queryUserByName(@Param("name")String name);

    @Select("select * from user_list where username=#{username}")
    public User queryUserByUsername(@Param("username") String username);

    @Select("select * from user_list where username=#{username} and password=#{password}")
    public User queryUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    public Integer addUser(User user);

    @Update("update user_list set avatar=#{avatar} where id=#{id}")
    public Integer updateUserAvatar(User user);
}
