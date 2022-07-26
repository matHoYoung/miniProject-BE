package com.fortune.fortune.controller;

import com.fortune.fortune.dto.LoginRequestDto;
import com.fortune.fortune.dto.LoginResponseDto;
import com.fortune.fortune.dto.SignupRequestDto;
import com.fortune.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    // 로그인 요청 처리
    @PostMapping("/user/signin")
    public LoginResponseDto loginUser(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto loginResponseDto = userService.loginUser(requestDto);
        return loginResponseDto;
    }
}