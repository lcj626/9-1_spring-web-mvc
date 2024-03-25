package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
* 핸들러 인터셉터를 구현한다.
* default 메소드 이전에는 모두 오버라이딩 해야 하는 책임을 가지기 때문에 JHandlerInterceptorAdaptor를 이용해 부담을 줄여 사용했으나
* default 메소드가 인터페이스에서 사용 가능하게 된 1.8 이후 부터는 인터페이스만 구현하여 필요한 메소드만 오버라이딩 해서 사용할 수 있다.
* */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }



    /*
    * 전처리 메소드
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!request.getParameter("auth").equals("admin")){
            response.sendRedirect("/");
            return false;
        }

        System.out.println("prehandler 호출함");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // 컨트롤러를 이어서 호출 한다. false인 경우 핸들러 메소드를 호출 하지 않는다.
        return true;
    }


    /*
    * 후처리 메소드
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler 호출함");
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);
    }

    @Override // HandlerInterceptor 를 사용하여 요청 처리가 완료된 후에 실행 되는
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after Complete 호출함");
        menuService.method();
    }
}
