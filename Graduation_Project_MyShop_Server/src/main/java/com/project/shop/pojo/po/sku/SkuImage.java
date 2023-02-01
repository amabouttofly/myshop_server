package com.project.shop.pojo.po.sku;

public class SkuImage {
    private Integer id;
    private Integer spuImageId;
    private Integer isDefault;
    private Integer skuId;
    private String imageName;
    private String imageUrl;

    @Override
    public String toString() {
        return "SkuImage{" +
                "id=" + id +
                ", spuImageId=" + spuImageId +
                ", isDefault=" + isDefault +
                ", skuId=" + skuId +
                ", imageName='" + imageName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpuImageId() {
        return spuImageId;
    }

    public void setSpuImageId(Integer spuImageId) {
        this.spuImageId = spuImageId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
