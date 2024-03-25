package com.ohgiraffers.fileupload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**") // 정적 리소스에 대한 핸들러 추가하는 메서드 img/** 요청시 응답 한다.
                .addResourceLocations("classpath:/static/img/") // 해당 요청에 매핑될 경로
                .setCachePeriod(20); // 클라이언트의 캐싱 유지 시작
    }
}
