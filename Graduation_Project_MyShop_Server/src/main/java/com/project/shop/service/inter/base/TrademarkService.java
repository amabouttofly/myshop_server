package com.project.shop.service.inter.base;

import com.project.shop.pojo.bo.LimitPageOfTrademarkBO;
import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TrademarkService {
    public LimitPageOfTrademarkBO getLimitPage(Integer currentPage, Integer pageLimit);

   public Trademark getTrademarkByTmName(String tmName);

    public Trademark getTrademarkById(Integer id);

   public String addTrademark(Trademark trademark);

    public String updateTrademarkById(Trademark trademark);

    public Integer deleteTrademarkById(Integer id);

    public AboutAdminResponse fileUploadForTrademarkLogoImg(String tmName, MultipartFile file, HttpServletRequest request);

    public List<Trademark> getTrademarkList();
}
