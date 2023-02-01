package com.project.shop.dao.user;

import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.sql.SkuReviewListSqlParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReviewMapper {

    @Select("select * from review_list where id = #{reviewId}")
    public Review getReviewByReviewId(@Param("reviewId")Integer reviewId);

    public Integer getItemsTotalFromUserRequest(SkuReviewListSqlParam param);

    public List<Review> getReviewListFromUserRequest(SkuReviewListSqlParam param);

    @Select("select count(*) from review_list where status = #{status}")
    public Integer getItemsTotalInStatus(@Param("status")String status);

    @Select("select * from review_list where status = #{status} limit #{index}, #{size}")
    public List<Review> getReviewLimitListByStatus(@Param("status")String status,
                                                   @Param("index")Integer index,
                                                   @Param("size")Integer size);

    @Select("select * from review_list where status = #{status}")
    public List<Review> getReviewListByStatus(@Param("status")String status);

    @Select("select * from review_list where orderDetailId = #{orderDetailId}")
    public Review getReviewByOrderDetailId(@Param("orderDetailId")Integer orderDetailId);

    public Integer addReview(Review review);

    @Update("update review_list set status = #{status} where id = #{reviewId}")
    public Integer updateReviewStatus(@Param("reviewId")Integer reviewId,
                                      @Param("status")String status);
}
