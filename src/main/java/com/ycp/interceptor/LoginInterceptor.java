package com.ycp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ycp on 2018/9/4.
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url=httpServletRequest.getRequestURI();
        if(url.indexOf("login.action")>=0){
            System.out.println("123");
            return true;
        }
        HttpSession session=httpServletRequest.getSession();
        String username= (String) session.getAttribute("username".trim());
        System.out.println(username+"12231");
        if(username != null){
            System.out.println("ycp+++");
            return true;
        }
        httpServletRequest.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(httpServletRequest,httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
