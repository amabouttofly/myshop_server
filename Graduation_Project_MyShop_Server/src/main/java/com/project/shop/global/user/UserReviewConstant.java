package com.project.shop.global.user;

import java.util.ArrayList;
import java.util.List;

public class UserReviewConstant {

    public static final String unapproved = "审核中";
    public static final String approved = "通过审核";
    public static final String approvedFailed = "审核未通过";

    public static List<String> getReviewStatusList(){
        List<String> list = new ArrayList<>();
        list.add(unapproved);
        list.add(approved);
        list.add(approvedFailed);
        return list;
    }
}
