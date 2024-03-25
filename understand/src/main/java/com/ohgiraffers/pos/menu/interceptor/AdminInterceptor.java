package com.ohgiraffers.pos.menu.interceptor;

import com.ohgiraffers.pos.menu.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public AdminInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!request.getParameter("auth").equals("admin")){
            response.sendRedirect("/");

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
