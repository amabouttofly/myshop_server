package com.project.shop.pojo.bo;

public class EmailCodeBo {
    private String email;
    private String code;

    @Override
    public String toString() {
        return "EmailCodeBo{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
