package com.ohgiraffers.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // exception 나면 오류를 위로 보내는게 아니라 여기서 처리하도록 해주는 것
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class) // 어떤 익셉션이 발생했을때 핸들링 할지 지정해 줘야 함
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("Global 레벨의 Exception 처리"); // 전역에서 일어난느 오류 <-> local 레벨
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception){
        System.out.println("Global 레벨의 Exception 처리");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }


    @ExceptionHandler(Exception.class)
    public String nullPointerExceptionHandler(Exception e){
        //System.out.println(e.getClass()); 어떤 유형의 에러인지 출력해 준다
        System.out.println("Exception 발생함");
        return "error/default";
    }
}
