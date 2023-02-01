package com.project.shop.controller.admin.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.FileGlobalConstant;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.service.TrademarkConstant;
import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.TrademarkService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RequestMapping("/admin/product/baseTrademark")
@RestController
public class BaseTrademarkController {

    private TrademarkService trademarkService;

    @GetMapping("/getLimitPage/{currentPage}/{pageLimit}")
    public String getLimitPage(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageLimit")Integer pageLimit) throws JsonProcessingException {
        System.out.println("搜索品牌页");
        AboutAdminResponse response=new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(trademarkService.getLimitPage(currentPage,pageLimit));
        System.out.println("response为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @PostMapping("/save")
    public String saveTrademark(@RequestBody Trademark trademark) throws JsonProcessingException {
        System.out.println("收到新增商标请求,数据:"+trademark);
        AboutAdminResponse response=new AboutAdminResponse();
        String result=trademarkService.addTrademark(trademark);
        response.setMessage(result);
        if (Objects.equals(result, TrademarkConstant.InsertSuccess)){
            response.setCode(AdminStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
        }
        System.out.println("controller层回应数据:"+response);
        return JsonUtils.getJsonString(response);
    }

    @PutMapping("/update")
    public String updateTrademark(@RequestBody Trademark trademark) throws JsonProcessingException {
        System.out.println("收到更新商标请求,数据:"+trademark);
        AboutAdminResponse response=new AboutAdminResponse();
        String result=trademarkService.updateTrademarkById(trademark);
        if (Objects.equals(result, TrademarkConstant.UpdateSuccess)){
            response.setCode(AdminStaticData.PassCode);
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
        }
        response.setMessage(result);
        System.out.println("响应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @DeleteMapping("/remove/{id}")
    public String removeTrademark(@PathVariable("id") Integer id) throws JsonProcessingException {
        System.out.println("收到删除请求,id为:"+id);
        AboutAdminResponse response=new AboutAdminResponse();
        Integer i=trademarkService.deleteTrademarkById(id);
        if (i>0) {
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("删除成功");
        } else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("删除失败");
        }
        return JsonUtils.getJsonString(response);
    }

    // 加上了produces属性,防止乱码
    @PostMapping(value = "/fileUpload", produces = "application/json;charset=utf-8")
    public String fileUpload(Trademark trademark, @RequestParam("uploadFile") MultipartFile file, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("收到商标图片上传请求");
        System.out.println(trademark);
        AboutAdminResponse response =new AboutAdminResponse();
        if (file!=null && trademark.getTmName()!=null && !trademark.getTmName().trim().equals("") ){
            Trademark selectTrademark=trademarkService.getTrademarkByTmName(trademark.getTmName());
            if (trademark.getId()!=null){
                // id存在,则一定为更新操作
                System.out.println("更新操作的文件上传");
                if (selectTrademark==null || Objects.equals(selectTrademark.getId(), trademark.getId())){
                    if (trademarkService.getTrademarkById(trademark.getId())!=null){
                        //Id存在且合法
                        response=trademarkService.fileUploadForTrademarkLogoImg(trademark.getTmName(),file,request);
                    }else {
                        response.setCode(FileGlobalConstant.ForbiddenCode);
                        response.setMessage("ID不合法");
                    }
                }else {
                    // 存在另一个品牌,id不同,但品牌名相同
                    response.setCode(FileGlobalConstant.ForbiddenCode);
                    response.setMessage("品牌名已经存在");
                }

            }else {
                // id为空则一定为插入操作,此时品牌名不能重复
                System.out.println("插入操作的文件上传");
                if (selectTrademark==null){
                    response=trademarkService.fileUploadForTrademarkLogoImg(trademark.getTmName(),file,request);
                }else {
                    response.setCode(FileGlobalConstant.ForbiddenCode);
                    response.setMessage("品牌名已经存在");
                }
            }
        }else {
            response.setCode(FileGlobalConstant.ForbiddenCode);
            response.setMessage("品牌名格式要合法且不为空");
        }
        System.out.println("文件上传回应数据为:"+response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getTrademarkList")
    public String getTrademarkList() throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setCode(AdminStaticData.PassCode);
        response.setData(trademarkService.getTrademarkList());
        return JsonUtils.getJsonString(response);
    }


    @Autowired
    public void setTrademarkService(TrademarkService trademarkService) {
        this.trademarkService = trademarkService;
    }
}
