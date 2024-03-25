package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorTestController {

    @PostMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함");
        Thread.sleep(1000);// 1초의 대기

        return "result";
    }
}
