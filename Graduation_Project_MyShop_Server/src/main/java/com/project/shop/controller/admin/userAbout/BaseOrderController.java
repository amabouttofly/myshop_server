package com.project.shop.controller.admin.userAbout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.order.OrderConstant;
import com.project.shop.global.user.UserReplyConstant;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.base.BaseOrderService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/userAbout/baseOrder")
@RestController
public class BaseOrderController {
    private BaseOrderService baseOrderService;

    @GetMapping("/getOrderDetail/{orderDetailId}")
    public String getOrderDetail(@PathVariable("orderDetailId")Integer orderDetailId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(baseOrderService.getOrderDetailById(orderDetailId));
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getOrderListByStatus/{status}/{currentPage}/{pageLimit}")
    public String getOrderListByStatus(@PathVariable("status")String status,
                                       @PathVariable("currentPage")Integer currentPage,
                                       @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(baseOrderService.getOrderListByStatus(status, currentPage, pageLimit));
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getAllOrderStatus")
    public String getAllOrderStatus() throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(OrderConstant.getOrderStatusList());
        return JsonUtils.getJsonString(response);
    }


    @Autowired
    public void setBaseOrderService(BaseOrderService baseOrderService) {
        this.baseOrderService = baseOrderService;
    }
}
