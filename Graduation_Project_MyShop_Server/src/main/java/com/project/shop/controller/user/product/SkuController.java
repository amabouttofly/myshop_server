package com.project.shop.controller.user.product;

import com.project.shop.service.inter.base.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user/product/sku")
@RestController
public class SkuController {

    private SkuService skuService;

//    @GetMapping("/getSkuSimpleInfo/{skuId}")
//    public String getSku(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
//        AboutUserResponse response = new AboutUserResponse();
//        response.setData(skuService.getSkuWithoutPropList(skuId));
//        response.setCode(UserStaticData.PassCode);
//        return JsonUtils.getJsonString(response);
//    }

    @Autowired
    public void setSkuService(SkuService skuService) {
        this.skuService = skuService;
    }
}
