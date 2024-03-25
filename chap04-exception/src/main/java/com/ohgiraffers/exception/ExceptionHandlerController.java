package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointerExceptionTest(){
        String str = null;
        System.out.println(str.charAt(0)); // null이라 charAt 반환 못하고 오류나니까 위로 던져버려서 출력 못하고 에러남
        return "/main";
    }

    @ExceptionHandler(NullPointerException.class) // 만들어 주면 위의 charAt이 오류 위로 안던지고 여기로 보내줌
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 exception 처리"); // 글로벌 레벨보다 지역단위가 우선순위 가지기 때문에 이게 제일 우선적으로 출력
        return "error/nullPointer";
    }


    @GetMapping("controller-user")
    public String userException() throws MemberRegistException{ //사용자가 만든거라 클래스 만들어 줘야 함
        boolean check = true;
        if(check){
            throw new MemberRegistException("입사가 불가능 합니다.");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String MemberRegistExceptionHandler(MemberRegistException e){
        System.out.println("멤버 레지스트에서 엑셉션 처리");
        return "error/memberRegist";
    }


}
