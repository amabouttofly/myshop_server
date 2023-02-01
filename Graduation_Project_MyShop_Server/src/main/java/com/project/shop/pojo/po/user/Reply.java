package com.project.shop.pojo.po.user;

import java.util.Date;

public class Reply {
    private Integer id;
    private String reply;
    private String status;
    private Date replyTime;
    private Integer reviewId;
    private Integer userId;
    private String userName;
    private Integer userLevel;
    private String userAvatar;
    private Integer replyId;
    private Integer replyUserId;
    private String replyUserName;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", reply='" + reply + '\'' +
                ", status='" + status + '\'' +
                ", replyTime=" + replyTime +
                ", reviewId=" + reviewId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userLevel=" + userLevel +
                ", userAvatar='" + userAvatar + '\'' +
                ", replyId=" + replyId +
                ", replyUserId=" + replyUserId +
                ", replyUserName='" + replyUserName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }
}
