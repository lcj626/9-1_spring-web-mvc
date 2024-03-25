package com.ohgiraffers.securitysession.config;

import com.ohgiraffers.securitysession.common.UserRole;
import com.ohgiraffers.securitysession.config.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    /*
    * 비밀번호를 인코딩 하기 위한 Bean
    * Bcrypt는 비밀번호 해싱에 가장 많이 사용 되는 알고리즘 중 하나이다.
    *
    * 사용 이유
    * 1. 보안성 : 해시 함수에 무작위 솔트를 적용하여 생성한다.
    * 2. 비용 증가 : 매개변수에 값을 주면 암호 생성 시간을 조절할 수 있어 무차별 공격을 어렵게 한다.
    * 3. 호환성 : 높은 보완 수준 및 데이터베이스에 저장하기 쉬운 특징
    * 4. 알고리즘 신뢰성 : 보안에 논의 평가를 거친 알고리즘으로 문제 없이 계속 사용 중
    * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // 정적 리소스에 대한 요청을 제외하겠다는 설정 static 파일 하위
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean // 스프링 필터 체인 설정 -> 어떤 요청은 우리가 어떻게 처리할게
    public SecurityFilterChain configure(HttpSecurity http) throws Exception { // 시큐리티필터체인 커스텀 설정

        http.authorizeHttpRequests(auth -> { // 서버의 리소스에 접근 가능한 권한을 설정함
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll(); // 이 요청에 대해 서는 모든 이들에게 허용해 주겠다.(인증,비인증 상관 없음)
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole()); // admin에 요청 들어가는 건 admin 권한(hasAuthority)을 가진 이 처리 하겠다
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole()); // user에 요청 들어오는 건 user 권한을 가진 이가 처리 하겠다.
            auth.anyRequest().authenticated(); // 모든 요청(anyRequest)에 대해 인증된 사용자(authenticated)들에 한 해 허용해 주겠다.
        }).formLogin(login ->{ //로그인 요청 어떻게 할 것이냐
            login.loginPage("/auth/login"); // 로그인 페이지에 해당 되는 서블릿이 존재해야 한다. 로그인 요청을 /auth/login 으로 바꾸겠다.
            login.usernameParameter("user"); // html에 인풋값 이름 조정
            login.passwordParameter("pass");
            login.defaultSuccessUrl("/"); // 로그인 성공시, 서블릿이 존재해야 한다.
            login.failureHandler(authFailHandler); // 로그인 실패 시 어떻게 할 것인가
        }).logout(logout ->{
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")); // 로그아웃 시 어떤 요청 날릴 것이냐, 별도 페이지 존재X 포스트 요청만 날리면 됨(get요청은 우리가 서블릿 만든거로 함)
            logout.deleteCookies("JSESSIONID"); // 사용자가 세션 못쓰게 쿠키 지움 세션에 대응 되는 키 값 잃어 버림. response 될 때 다시 만들어서 보내줌
            logout.invalidateHttpSession(true); // 세션을 소멸하도록 허용하는 것 로그아웃됨
            logout.logoutSuccessUrl("/"); // 로그아웃 시 이동할 페이지 설정 -> 이제 세션관리 해 줘야 함
        }).sessionManagement(session ->{
            session.maximumSessions(1); // 세션 개수 한 개로 제한 중복 로그인 X
            session.invalidSessionUrl("/"); // 세션 만료 시 이동할 페이지
        }).csrf(csrf -> csrf.disable() );

        return http.build(); //SecurityFilterChain 객체로 반환
    }
}
