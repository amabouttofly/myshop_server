package com.project.shop.pojo.po.attr;

public class Trademark {
    private Integer id;
    private String tmName;
    private String logoUrl;

    @Override
    public String toString() {
        return "Trademark{" +
                "id=" + id +
                ", tmName='" + tmName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
