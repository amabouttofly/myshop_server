package com.project.shop.Interceptor;

import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.utils.JsonUtils;
import com.project.shop.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("admin拦截器---preHandle");
        String token = request.getHeader("X-Token");
        if (JwtUtils.checkToken(token, JwtUtils.adminSignature)){
            System.out.println("token解析成功");
            return true;
        }
        System.out.println("token解析失败");
        AboutAdminResponse aboutAdminResponse=new AboutAdminResponse();
        aboutAdminResponse.setCode(AdminStaticData.IllegalTokenCode);
        aboutAdminResponse.setMessage(AdminStaticData.IllegalTokenMessage);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JsonUtils.getJsonString(aboutAdminResponse));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("admin拦截器---postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("admin拦截器---afterCompletion");
    }


}
