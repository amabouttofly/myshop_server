package com.project.shop.controller.admin.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.AttrService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/product/baseAttr")
@RestController
public class BaseAttrController {
    private AttrService attrService;

    @GetMapping("/getAttrInfoListByCategory3Id/{category3Id}")
    private String getAttrInfoList(@PathVariable("category3Id") Integer category3Id) throws JsonProcessingException {
        AboutAdminResponse response=new AboutAdminResponse();
        List<AttrKey> attrKeyList = attrService.getAttrKeyListByCategory3Id(category3Id);
        response.setCode(AdminStaticData.PassCode);
        response.setData(attrKeyList);
        System.out.println(response);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/saveAttrInfo")
    private String saveAttrInfo(@RequestBody AttrKey attrKey) throws JsonProcessingException {
        System.out.println("收到保存属性请求,数据:"+attrKey);
        AboutAdminResponse response = attrService.saveAttrInfo(attrKey);
        System.out.println("回应请求的数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping("/deleteAttrInfo/{attrId}")
    private String deleteAttrInfo(@PathVariable("attrId") Integer attrId) throws JsonProcessingException {
        System.out.println("收到删除属性请求,属性id为:"+attrId);
        return JsonUtils.getJsonString(attrService.deleteAttrInfo(attrId));
    }

    @Autowired
    public void setAttrService(AttrService attrService) {
        this.attrService = attrService;
    }
}
