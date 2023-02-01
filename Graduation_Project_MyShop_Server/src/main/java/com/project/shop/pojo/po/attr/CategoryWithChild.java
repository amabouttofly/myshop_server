package com.project.shop.pojo.po.attr;

import java.util.List;

public class CategoryWithChild {
    private String categoryId;
    private String categoryName;
    private List<CategoryWithChild> categoryChild;

    @Override
    public String toString() {
        return "CategoryWithChild{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryChild=" + categoryChild +
                '}';
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryWithChild> getCategoryChild() {
        return categoryChild;
    }

    public void setCategoryChild(List<CategoryWithChild> categoryChild) {
        this.categoryChild = categoryChild;
    }
}
