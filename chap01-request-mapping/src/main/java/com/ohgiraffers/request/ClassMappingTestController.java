package com.ohgiraffers.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // 사용자의 요청을 받아 주는 서블릿
//@RestController //데이터만 왔다 갔다.
@RequestMapping("/order/*") // 어노테이션의 밸류 속성, 어떤 속성이 들어왔을 때 여기서 처리해 줄게
public class ClassMappingTestController {

    // port : 80 = web
    // port : 8080 = was
    // port : 443 = https

    // Get : localhost:8000/order/regist -> 이 요청이 들어오면 매핑 해 주겠다
     @GetMapping("/regist") //
    public String regitstOrder(Model model){
        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출");
        // "get 방식의 주분~호출" value를 "message" name 에 추가

        return "mappingResult";
    }


    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model){

         model.addAttribute("message", "post 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러");
         // post 주문 수정과 주문 삭제 버튼 둘다 누르면 이거 뜸

         return "mappingResult";

    }

    /*
    * 3. path Variable
    * @PathVariable 어노테이션을 이용해 요청을 path로부터 변수를 받아 올 수 있다.
    * path variable로 전달 되는 {변수명} 값은 반드시 매개변수명과 동일해야 한다.
    * 만약 동일하지 않으면 @PathVariable("이름")을 설정 해주어야 한다.
    * 이는 rest형 웹 서비스를 설계할 때 유용하게 사용 된다.
    *
    * 인텔리제이의 builder 설정을 Intellij로 했을 경우에는 @PathVariable에 이름을 지정 해주지 않으면 찾지 못한다.
    * */

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo){
         model.addAttribute("message", orderNo+"번 주문 상세 내용 조회용 핸들러 메소드 호출");

         return "mappingResult";
    }

    @RequestMapping
    public String otherRequest(Model model){
         model.addAttribute("message", "order 요청이긴 하지만 다른 기능이 준비 되지 않음");
         return "mappingResult";
    }
}
