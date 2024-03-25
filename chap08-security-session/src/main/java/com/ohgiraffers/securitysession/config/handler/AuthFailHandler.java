package com.ohgiraffers.securitysession.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

/*
* 사용자의 로그인 실패 시
* 실패 요청을 커스텀 하기 위한 핸들러 이다.
*
* 패키지 구조
* AuthenticationFailureHandler(interface) -> SimpleUrlAuthenticationFailureHandler(class) -> AuthFailHandler
*
* 우리는 AuthenticationFailureHandler 를 구현해야 하지만 기존에 구현이 되었는 SimpleUrlAuthenticationFailureHandler를 상속 받아
* 응답 메세지와 페이지 경로를 설정 할 수 있도록 하기 위해서 재정의를 하는 것이다.
* 페이지 경로와 커스텀을 할 수 있도록 만들어 주는 메서드는 setDefaultFailureUrl("경로") 메서드 이다.
* */


@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = null;
        if(exception instanceof BadCredentialsException){
            // BadCredentialsException 오류는 사용자의 아이디가 DB에 존재하지 않는 경우 비밀번호가 맞지 않는 경우 발생 한다.
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        }else if(exception instanceof InternalAuthenticationServiceException){
            // 서버에서 사용자 정보를 검증하는 과정에서 발생하는 에러이다.
            errorMessage = "서버에서 오류가 발생되었습니다.";
        }else if(exception instanceof UsernameNotFoundException){
            // db에 사용자의 정보가 없는 경우 발생하는 오류이다.
            errorMessage = "존재하지 않는 이메일 입니다.";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            // 보안 컨텍스트에 인증 개게가 존재하지 않거나 인증 정보가 없는 상태에서 보안처리된 리소스에 접근하는 경우 발생
            errorMessage = "인증 요청이 거부되었습니다.";
        }else{
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/auth/fail?message="+errorMessage);

        super.onAuthenticationFailure(request, response, exception); // 부모한테 넘겨줌
    }
}
