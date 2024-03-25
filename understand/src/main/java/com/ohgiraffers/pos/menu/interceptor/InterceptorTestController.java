package com.ohgiraffers.pos.menu.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorTestController {

    @PostMapping("allMenu")
    public String handlerMethod() {

        return "result";
    }


}
