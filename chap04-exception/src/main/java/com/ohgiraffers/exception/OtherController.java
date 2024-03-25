package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {


    @GetMapping("other-controller-null")
    public String otherNullPointerException(){
        String str = null;
        System.out.println(str.charAt(0));
        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserExceptionTest() throws MemberRegistException{
        if(true){
            throw new MemberRegistException("입사 불가");
        }
        return "/";
    }



    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest(){
        double[] array = new double[0]; // 배열 길이 0
        System.out.println(array[0]); // 배열의 길이가 0이면 0번째 참조 X 배열의 길이가 1이어야 0번째 참조 -> OutOfBoundException ->but 설정해 준게 없으니 그냥 Exception에서 처리함
        return "/";
    }


}
