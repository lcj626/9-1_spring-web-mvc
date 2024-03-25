package com.ohgiraffers.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chap04ExceptionApplication { //따로 설정 안해주면 처음 프로젝트 생성할때 설정한 패키지(exception)까지 실행 만약 하위에 test package 만들고 집어넣으면 실행 안됨

    public static void main(String[] args) {
        SpringApplication.run(Chap04ExceptionApplication.class, args);
    }

}
