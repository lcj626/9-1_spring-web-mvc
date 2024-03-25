package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private StopWatchInterceptor stopWatchInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopWatchInterceptor)
                .addPathPatterns("/stopwatch")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**") // images의 모든 하청 요구들은 받지 않는다.
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/error"); // 제외 하겠다.
    }
}
