package com.project.shop.global.user;

import java.util.ArrayList;
import java.util.List;

public class UserInfoConstant {

    public static final String defaultAvatar = "http://localhost:8080/img/userAvatar/defaultUserAvatar.png";

    public static final String defaultAvatarPath = "http://localhost:8080/img/userAvatar/";

    public static final String defaultAvatar1 = "default01.png";
    public static final String defaultAvatar2 = "default02.png";
    public static final String defaultAvatar3 = "default03.png";
    public static final String defaultAvatar4 = "default04.png";
    public static final String defaultAvatar5 = "default05.png";
    public static final String defaultAvatar6 = "default06.png";
    public static final String defaultAvatar7 = "default07.png";
    public static final String defaultAvatar8 = "default08.png";
    public static final String defaultAvatar9 = "default09.png";
    public static final String defaultAvatar10 = "default10.png";
    public static final String defaultAvatar11= "default11.png";
    public static final String defaultAvatar12 = "default12.png";

    public static final Integer defaultUserLevel = 1;

    public static List<String> getAllDefaultAvatar(){
        List<String> list = new ArrayList<>();
        list.add(defaultAvatarPath + defaultAvatar1);
        list.add(defaultAvatarPath + defaultAvatar2);
        list.add(defaultAvatarPath + defaultAvatar3);
        list.add(defaultAvatarPath + defaultAvatar4);
        list.add(defaultAvatarPath + defaultAvatar5);
        list.add(defaultAvatarPath + defaultAvatar6);
        list.add(defaultAvatarPath + defaultAvatar7);
        list.add(defaultAvatarPath + defaultAvatar8);
        list.add(defaultAvatarPath + defaultAvatar9);
        list.add(defaultAvatarPath + defaultAvatar10);
        list.add(defaultAvatarPath + defaultAvatar11);
        list.add(defaultAvatarPath + defaultAvatar12);
        return list;
    }

}
