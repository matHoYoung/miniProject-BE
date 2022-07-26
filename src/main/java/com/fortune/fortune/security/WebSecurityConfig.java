package com.fortune.fortune.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        // 회원 관리 처리 API (POST /api/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/user/**")
//                .ignoringAntMatchers("/api/**");

        // CSRF protection 을 비활성화
        http.csrf().disable();


        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/signin").permitAll()
                .antMatchers("/user/signup").permitAll()
                // 전부를 login 없이 허용
//                .antMatchers("/**").permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()

//                .and()
                    // [로그인 기능]
//                    .formLogin()
//                    // 로그인 View 제공 (GET /user/login)
//                    .loginPage("/api/user/login")
                    // 로그인 처리 (POST /user/login)
//                    .loginProcessingUrl("/api/user/login").permitAll()
                    // 로그인 처리 후 실패 시 URL
//                    .defaultSuccessUrl("/")
//                    .failureUrl("/api/user/login?error")
//                    .permitAll()

                .and()
                    // [로그아웃 기능]
                    .logout()
                    // 로그아웃 처리 URL
                    .logoutUrl("/user/logout")
                    .permitAll();
                http
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());


    }

    private static class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");


            objectMapper.writeValue(response.getWriter(), "로그인 후 이용해주세요");
        }
    }
}