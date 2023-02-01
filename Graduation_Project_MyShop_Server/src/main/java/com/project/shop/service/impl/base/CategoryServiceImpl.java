package com.project.shop.service.impl.base;

import com.project.shop.dao.attr.CategoryMapper;
import com.project.shop.pojo.po.attr.Category;
import com.project.shop.pojo.po.attr.CategoryWithChild;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.service.inter.base.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Override
    public Integer getCategory3IdByAllLevel(SearchSkuRequest searchSkuRequest) {
        return categoryMapper.getCategory3IdByAllLevel(searchSkuRequest);
    }

    @Override
    public List<CategoryWithChild> getAllLevelCategoryList() {
        return categoryMapper.getAllLevelCategoryList();
    }

    @Override
    public List<Category> getCategory1List() {
        return categoryMapper.getCategory1List();
    }

    @Override
    public List<Category> getCategory2ListByCategory1Id(Integer category1Id) {
        return categoryMapper.getCategory2ListByCategory1Id(category1Id);
    }

    @Override
    public List<Category> getCategory3ListByCategory2Id(Integer category2Id) {
        return categoryMapper.getCategory3ListByCategory2Id(category2Id);
    }


    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
