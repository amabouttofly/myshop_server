package com.project.shop.pojo.po.spu;

import java.util.List;

public class Spu {
    private Integer id;
    private String spuName;
    private String description;
    private Integer category3Id;
    private Integer tmId;

    private List<SpuSaleAttrKey> spuSaleAttrKeyList;

    private List<SpuImage> spuImageList;

    @Override
    public String toString() {
        return "Spu{" +
                "id=" + id +
                ", spuName='" + spuName + '\'' +
                ", description='" + description + '\'' +
                ", category3Id=" + category3Id +
                ", tmId=" + tmId +
                ", spuSaleAttrKeyList=" + spuSaleAttrKeyList +
                ", spuImageList=" + spuImageList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public Integer getTmId() {
        return tmId;
    }

    public void setTmId(Integer tmId) {
        this.tmId = tmId;
    }

    public List<SpuSaleAttrKey> getSpuSaleAttrKeyList() {
        return spuSaleAttrKeyList;
    }

    public void setSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        this.spuSaleAttrKeyList = spuSaleAttrKeyList;
    }

    public List<SpuImage> getSpuImageList() {
        return spuImageList;
    }

    public void setSpuImageList(List<SpuImage> spuImageList) {
        this.spuImageList = spuImageList;
    }
}
