package com.project.shop.controller.admin.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.po.spu.Spu;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.SpuService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin/product/baseSpu")
@RestController
public class BaseSpuController {

    private SpuService spuService;

    @GetMapping("/getLimitPage/{currentPage}/{pageLimit}")
    public String getLimitPage(@PathVariable("currentPage")Integer currentPage,
                               @PathVariable("pageLimit")Integer pageLimit,
                               Integer category3Id) throws JsonProcessingException {
        System.out.println("收到spu分页请求,currentPage,pageLimit,category3Id为:"+currentPage+"--"+pageLimit+"--"+category3Id);
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(spuService.getSpuListLimitPage(currentPage, pageLimit, category3Id));
        response.setCode(AdminStaticData.PassCode);
        System.out.println("回应spu分页请求数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getSpuInfoById/{spuId}")
    public String getSpuInfoById(@PathVariable("spuId")Integer spuId) throws JsonProcessingException {
        System.out.println("收到getSpuInfo请求,spuId为:"+spuId);
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(spuService.getSpuById(spuId));
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getSaleAttrKeyList")
    public String getSaleAttrKeyList() throws JsonProcessingException {
        System.out.println("收到获取所有销售属性请求");
        AboutAdminResponse response =new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(spuService.getSaleAttrKeyList());
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/spuImageList/{spuId}")
    public String getSpuImageListById(@PathVariable("spuId")Integer spuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(spuService.getSpuImageListBySpuId(spuId));
        response.setCode(AdminStaticData.PassCode);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/spuSaleAttrList/{spuId}")
    public String getSpuSaleAttrList(@PathVariable("spuId") Integer spuId) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(spuService.getSpuSaleAttrList(spuId));
        response.setCode(AdminStaticData.PassCode);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody Spu spu) throws JsonProcessingException {
        System.out.println("收到spu保存请求,数据为:"+spu);
        AboutAdminResponse response = spuService.saveSpuInfo(spu);
        System.out.println("回应spu保存请求的数据为:"+response);
        return JsonUtils.getJsonString(response);
    }


    @PostMapping(value = "/fileUpload", produces = "application/json;charset=utf-8")
    public String spuImageFileUpload(String spuName, Integer category3Id, Integer spuId,@RequestParam("spuImagesUploadFile") MultipartFile file, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到spu图片上传请求,spu名称为:"+spuName+"====category3Id:"+category3Id+"======spuId:"+spuId);
        AboutAdminResponse response = spuService.SpuImageFileUpload(spuName,category3Id,spuId,file,request);
        System.out.println("回应图片上传的数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping("/deleteSpuInfo/{spuId}")
    public String deleteSpuInfo(@PathVariable("spuId") Integer spuId) throws JsonProcessingException {
        System.out.println("收到spu删除请求,spuId为:"+spuId);
        AboutAdminResponse response = new AboutAdminResponse();
        if (spuService.deleteSpuById(spuId) == 1) {
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("删除成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("删除失败");
        }
        return JsonUtils.getJsonString(response);
    }

    @Autowired
    public void setSpuService(SpuService spuService) {
        this.spuService = spuService;
    }
}
