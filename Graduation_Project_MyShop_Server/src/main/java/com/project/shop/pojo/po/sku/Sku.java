package com.project.shop.pojo.po.sku;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Sku {
    private Integer id;
    private String price;
    private String weight;
    private Integer category3Id;
    private Integer tmId;
    private Integer spuId;
    private String skuName;
    private String skuDesc;
    private String skuDefaultImage;
    private Integer isSale;
    private Date createTime;

    private List<SkuAttrValue> skuAttrValueList;
    private List<SkuSaleAttrValue> skuSaleAttrValueList;
    private List<SkuImage> skuImageList;

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", price=" + price +
                ", weight=" + weight +
                ", category3Id=" + category3Id +
                ", tmId=" + tmId +
                ", spuId=" + spuId +
                ", skuName='" + skuName + '\'' +
                ", skuDesc='" + skuDesc + '\'' +
                ", skuDefaultImage='" + skuDefaultImage + '\'' +
                ", isSale=" + isSale +
                ", createTime=" + createTime +
                ", skuAttrValueList=" + skuAttrValueList +
                ", skuSaleAttrValueList=" + skuSaleAttrValueList +
                ", skuImageList=" + skuImageList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public String getSkuDefaultImage() {
        return skuDefaultImage;
    }

    public void setSkuDefaultImage(String skuDefaultImage) {
        this.skuDefaultImage = skuDefaultImage;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SkuAttrValue> getSkuAttrValueList() {
        return skuAttrValueList;
    }

    public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
        this.skuAttrValueList = skuAttrValueList;
    }

    public List<SkuSaleAttrValue> getSkuSaleAttrValueList() {
        return skuSaleAttrValueList;
    }

    public void setSkuSaleAttrValueList(List<SkuSaleAttrValue> skuSaleAttrValueList) {
        this.skuSaleAttrValueList = skuSaleAttrValueList;
    }

    public List<SkuImage> getSkuImageList() {
        return skuImageList;
    }

    public void setSkuImageList(List<SkuImage> skuImageList) {
        this.skuImageList = skuImageList;
    }
}
