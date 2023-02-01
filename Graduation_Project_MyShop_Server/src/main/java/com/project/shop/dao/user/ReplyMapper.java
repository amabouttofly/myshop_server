package com.project.shop.dao.user;

import com.project.shop.pojo.po.user.Reply;
import com.project.shop.pojo.po.user.Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReplyMapper {

    @Select("select count(*) from reply_list where reviewId= #{reviewId}")
    public Integer getItemsTotalByReviewId(@Param("reviewId")Integer reviewId);

    public List<Reply> getReplyLimitListByReviewId(@Param("reviewId")Integer reviewId,
                                                            @Param("index")Integer index,
                                                            @Param("size")Integer size);

    @Select("select count(*) from reply_list where status = #{status}")
    public Integer getItemsTotalInStatus(@Param("status")String status);

    @Select("select * from reply_list where status = #{status} limit #{index}, #{size}")
    public List<Reply> getReplyLimitListByStatus(@Param("status")String status,
                                                   @Param("index")Integer index,
                                                   @Param("size")Integer size);

    public List<Reply> getReplyLimitListByReviewIdAndStatus(@Param("reviewId")Integer reviewId,
                                                            @Param("status")String status,
                                                            @Param("index")Integer index,
                                                            @Param("size")Integer size);

    @Select("select count(*) from reply_list where reviewId= #{reviewId} and status = #{status}")
    public Integer getItemsTotalByReviewIdAndStatus(@Param("reviewId")Integer reviewId,
                                                    @Param("status")String status);

    public Integer addReply (Reply reply);

    @Update("update reply_list set status = #{status} where id = #{replyId}")
    public Integer updateReplyStatus(@Param("replyId")Integer replyId,
                                      @Param("status")String status);
}
