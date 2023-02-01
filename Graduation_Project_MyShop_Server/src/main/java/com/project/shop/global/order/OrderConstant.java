package com.project.shop.global.order;

import java.util.ArrayList;
import java.util.List;

public class OrderConstant {
    public static final String unpaid = "UNPAID";
    public static final String paid = "PAID";
    public static final String online = "ONLINE";
    public static final Integer effectiveDateNum = 1;
    public static final Integer hasStock = 1;

    public static final Integer hasReview = 1;

    public static final String businessName = "繁华特产商城";

    public static List<String> getOrderStatusList(){
        List<String> list = new ArrayList<>();
        list.add(unpaid);
        list.add(paid);
        return list;
    }


}
