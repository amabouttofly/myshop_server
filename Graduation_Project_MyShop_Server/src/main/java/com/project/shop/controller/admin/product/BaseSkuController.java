package com.project.shop.controller.admin.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.po.sku.Sku;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.SkuService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/product/baseSku")
@RestController
public class BaseSkuController {

    private SkuService skuService;

    @GetMapping("/getSkuList/{currentPage}/{pageLimit}")
    public String getLimitPageOfSkuList(@PathVariable("currentPage")Integer currentPage,
                                        @PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        System.out.println("收到sku列表分页请求,currentPage:"+currentPage+"---pageLimit:"+pageLimit);
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setMessage("获取数据成功");
        response.setData(skuService.getSkuListLimitPage(currentPage, pageLimit));
        return JsonUtils.getJsonString(response);
    }


    @PostMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody Sku sku) throws JsonProcessingException {
        System.out.println("收到sku保存请求,sku数据为:"+sku);
        AboutAdminResponse response = skuService.saveSku(sku);
        System.out.println("回应sku保存数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getSkuListBySpuId/{spuId}")
    public String getSkuListBySpuId(@PathVariable("spuId") Integer spuId) throws JsonProcessingException {
        System.out.println("收到请求sku列表,spuId为:"+spuId);
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(skuService.getSkuListBySpuId(spuId));
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/onSale/{skuId}")
    public String onSale(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        if (skuService.onSale(skuId)) {
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("上架成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("上架失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/cancelSale/{skuId}")
    public String cancelSale(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        if (skuService.cancelSale(skuId)){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("下架成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("下架失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getSkuBySkuId/{skuId}")
    public String getSkuBySkuId(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(skuService.getSkuBySkuId(skuId));
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping("/deleteSku/{skuId}")
    public String deleteSku(@PathVariable("skuId")Integer skuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        if (skuService.deleteSku(skuId)){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("删除成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("删除失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setSkuService(SkuService skuService) {
        this.skuService = skuService;
    }
}
