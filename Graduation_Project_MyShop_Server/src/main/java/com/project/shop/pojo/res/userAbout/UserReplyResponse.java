package com.project.shop.pojo.res.userAbout;

import com.project.shop.pojo.po.user.Reply;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserReplyResponse {
    private Integer id;
    private String reply;
    private String status;
    private String replyTime;
    private Integer reviewId;
    private Integer userId;
    private String userName;
    private Integer userLevel;
    private String userAvatar;
    private Integer replyId;
    private Integer replyUserId;
    private String replyUserName;

    public UserReplyResponse(Reply reply){
        this.id = reply.getId();
        this.reply = reply.getReply();
        this.status = reply.getStatus();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.replyTime = dateFormat.format(reply.getReplyTime());
        this.reviewId = reply.getReviewId();
        this.userId = reply.getUserId();
        this.userName = reply.getUserName();
        this.userLevel = reply.getUserLevel();
        this.userAvatar = reply.getUserAvatar();
        this.replyId = reply.getReplyId();
        this.replyUserId = reply.getReplyUserId();
        this.replyUserName = reply.getReplyUserName();
    }

    @Override
    public String toString() {
        return "UserReplyResponse{" +
                "id=" + id +
                ", reply='" + reply + '\'' +
                ", status='" + status + '\'' +
                ", replyTime='" + replyTime + '\'' +
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

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
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
