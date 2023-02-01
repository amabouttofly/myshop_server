package com.project.shop.controller.admin.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.CategoryService;
import com.project.shop.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/product/baseCategory")
@RestController
public class BaseCategoryController {

    private CategoryService categoryService;

    @GetMapping("/getCategory1List")
    public String getCategory1List() throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(categoryService.getCategory1List());
        response.setCode(AdminStaticData.PassCode);
        System.out.println(response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getCategory2List/{category1Id}")
    public String getCategory2List(@PathVariable("category1Id") Integer category1Id) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(categoryService.getCategory2ListByCategory1Id(category1Id));
        response.setCode(AdminStaticData.PassCode);
        System.out.println(response);
        return JsonUtils.getJsonString(response);
    }

    @GetMapping("/getCategory3List/{category2Id}")
    public String getCategory3List(@PathVariable("category2Id") Integer category2Id) throws JsonProcessingException {
        AboutAdminResponse response = new AboutAdminResponse();
        response.setData(categoryService.getCategory3ListByCategory2Id(category2Id));
        response.setCode(AdminStaticData.PassCode);
        System.out.println(response);
        return JsonUtils.getJsonString(response);
    }


    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
