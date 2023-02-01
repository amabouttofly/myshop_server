package com.project.shop.dao.attr;

import com.project.shop.pojo.po.attr.Category;
import com.project.shop.pojo.po.attr.CategoryWithChild;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.sql.CategoryViewSqlResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {

    public CategoryViewSqlResult getCategoryView(@Param("category3Id")Integer category3Id);
    public Integer getCategory3IdByAllLevel(SearchSkuRequest searchSkuRequest);
    public List<CategoryWithChild> getAllLevelCategoryList();

    @Select("select * from category_first")
    public List<Category> getCategory1List();

    @Select("select * from category_second where category1Id=#{category1Id} ")
    public List<Category> getCategory2ListByCategory1Id(@Param("category1Id")Integer category1Id);

    @Select("select * from category_third where category2Id=#{category2Id} ")
    public List<Category> getCategory3ListByCategory2Id(@Param("category2Id")Integer category2Id);
}
