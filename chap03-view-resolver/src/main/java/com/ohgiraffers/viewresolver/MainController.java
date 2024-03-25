package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/main"}) //"/" localhost:8080/을 나타냄 서버에 기본 요청을 날림
    public String main(){return "main";} // "/" 랑 main 두개가 main.html 로 보내짐 but if main.html이 static 폴더에 가면 오류남  static 디렉토리는 값이 없는 기본값일 때 출력(정적)

}
