package com.project.shop.pojo.res.userAbout;

import com.project.shop.pojo.po.user.Review;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserReviewResponse {
    private Integer id;
    private String review;
    private Integer reviewRate;
    private String status;
    private String reviewTime;
    private Integer skuId;
    private Integer userId;
    private Integer userLevel;
    private String userName;
    private String userAvatar;
    private Integer orderDetailId;

    public UserReviewResponse(Review review){
        this.id = review.getId();
        this.review = review.getReview();
        this.reviewRate = review.getReviewRate();
        this.status = review.getStatus();
        // DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
        // this.reviewTime = mediumFormat.format(review.getReviewTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.reviewTime = dateFormat.format(review.getReviewTime());
        this.skuId = review.getSkuId();
        this.userId = review.getUserId();
        this.userLevel = review.getUserLevel();
        this.userName = review.getUserName();
        this.userAvatar = review.getUserAvatar();
        this.orderDetailId = review.getOrderDetailId();
    }

    @Override
    public String toString() {
        return "UserReviewResponse{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", reviewRate=" + reviewRate +
                ", status='" + status + '\'' +
                ", reviewTime='" + reviewTime + '\'' +
                ", skuId=" + skuId +
                ", userId=" + userId +
                ", userLevel=" + userLevel +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", orderDetailId=" + orderDetailId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(Integer reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
