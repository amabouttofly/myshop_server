package com.project.shop.pojo.sql;

public class CategoryViewSqlResult {
    private Integer category1Id;
    private String category1Name;
    private Integer category2Id;
    private String category2Name;
    private Integer category3Id;
    private String category3Name;

    @Override
    public String toString() {
        return "CategoryViewSqlResult{" +
                "category1Id=" + category1Id +
                ", category1Name='" + category1Name + '\'' +
                ", category2Id=" + category2Id +
                ", category2Name='" + category2Name + '\'' +
                ", category3Id=" + category3Id +
                ", category3Name='" + category3Name + '\'' +
                '}';
    }

    public Integer getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public Integer getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public String getCategory3Name() {
        return category3Name;
    }

    public void setCategory3Name(String category3Name) {
        this.category3Name = category3Name;
    }
}
