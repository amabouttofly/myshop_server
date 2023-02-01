package com.project.shop.service.inter.base;

import com.project.shop.pojo.po.attr.Category;
import com.project.shop.pojo.po.attr.CategoryWithChild;
import com.project.shop.pojo.req.product.SearchSkuRequest;

import java.util.List;

public interface CategoryService {

    public Integer getCategory3IdByAllLevel(SearchSkuRequest searchSkuRequest);
    public List<CategoryWithChild> getAllLevelCategoryList();
    public List<Category> getCategory1List();
    public List<Category> getCategory2ListByCategory1Id(Integer category1Id);
    public List<Category> getCategory3ListByCategory2Id(Integer category2Id);
}
