package com.project.shop.service.impl.user;

import com.project.shop.dao.sku.SkuMapper;
import com.project.shop.dao.trade.OrderDetailMapper;
import com.project.shop.dao.user.ReviewMapper;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.global.controller.UserStaticData;
import com.project.shop.global.order.OrderConstant;
import com.project.shop.global.user.UserReviewConstant;
import com.project.shop.pojo.po.trade.OrderDetail;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.req.userAbout.SkuReviewListRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.pojo.res.userAbout.UserReviewResponse;
import com.project.shop.pojo.sql.SkuReviewListSqlParam;
import com.project.shop.service.inter.user.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewMapper reviewMapper;
    private OrderDetailMapper orderDetailMapper;
    private SkuMapper skuMapper;

    @Override
    public AboutUserResponse getSkuReviewList(SkuReviewListRequest request) {
        AboutUserResponse response = new AboutUserResponse();
        SkuReviewListSqlParam sqlParam = new SkuReviewListSqlParam();

        sqlParam.setStatus(UserReviewConstant.approved);
        sqlParam.setSkuId(request.getSkuId());
        if (request.getReviewRateList() != null && request.getReviewRateList().size() >0){
            sqlParam.setReviewRateList(request.getReviewRateList());
        }

        Integer itemsTotal = reviewMapper.getItemsTotalFromUserRequest(sqlParam);
        Integer currentPage = request.getCurrentPage();
        Integer pageLimit = request.getPageLimit();

        if (itemsTotal==null) itemsTotal=0;
        if (pageLimit < 1) pageLimit = 1;
        if (currentPage < 1) currentPage = 1;

        while (pageLimit * (currentPage - 1) >= itemsTotal) {
            if(currentPage == 1) {
                break;
            }
            currentPage--;
        }
        Integer pagesTotal = itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;

        sqlParam.setSize(size);
        sqlParam.setIndex(index);

        if (request.getOrder() != null
                && !request.getOrder().trim().equals("")) {
            String[] arr = request.getOrder().split(":");
            if (arr.length == 2){
                if (arr[1].equals("asc") || arr[1].equals("desc")){
                    sqlParam.setOrderType(arr[1]);
                    sqlParam.setOrderOf(arr[0]);
                }
            }
        }

        List<Review> reviewList = reviewMapper.getReviewListFromUserRequest(sqlParam);

        List<UserReviewResponse> reviewResponseList = new ArrayList<>();
        for (Review review: reviewList) {
            UserReviewResponse reviewResponse = new UserReviewResponse(review);
            reviewResponseList.add(reviewResponse);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("currentPage",currentPage);
        data.put("pageLimit",pageLimit);
        data.put("itemsTotal", itemsTotal);
        data.put("pagesTotal",pagesTotal);
        data.put("reviewList",reviewResponseList);

        response.setCode(UserStaticData.PassCode);
        response.setData(data);
        return response;
    }

    @Override
    public Review getReviewByOrderDetailId(Integer orderDetailId) {
        return reviewMapper.getReviewByOrderDetailId(orderDetailId);
    }

    @Override
    public AboutUserResponse submitUserReview(Review review) {
        AboutUserResponse response = new AboutUserResponse();
        if (review.getReviewRate() >5
                || review.getReviewRate() < 1
                || review.getReview() == null
                || review.getReview().trim().equals("")
                || review.getSkuId() ==null
                || review.getOrderDetailId() == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("评论内容缺少");
            return response;
        }

        if (skuMapper.getSkuWithoutPropList(review.getSkuId()) == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("skuId不存在");
            return response;
        }
        OrderDetail orderDetail = orderDetailMapper.getOrderDetail(review.getOrderDetailId());
        if (orderDetail == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("订单详情Id不存在");
            return response;
        }
        if (Objects.equals(orderDetail.getHasReview(), OrderConstant.hasReview)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("订单已经评价过了");
            return response;
        }


        review.setStatus(UserReviewConstant.unapproved);
        // 数据库中 datetime为8字节,范围从1000到9999年 timestamp为4字节1970到2038
        // java的util.Date可以转换为timestamp, mybatis没有支持datetime的自动映射
        review.setReviewTime(new Date());
        if (reviewMapper.addReview(review) == 1){
            orderDetailMapper.updateOrderDetailReviewed(review.getOrderDetailId(), OrderConstant.hasReview);
            response.setCode(UserStaticData.PassCode);
            response.setMessage("添加评论成功");
        }else {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("添加评论失败");
        }
        return response;
    }



    @Autowired
    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }
    @Autowired
    public void setOrderDetailMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }
    @Autowired
    public void setSkuMapper(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }
}
