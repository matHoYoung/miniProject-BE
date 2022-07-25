package com.fortune.fortune.controller;

import com.fortune.fortune.dto.DiaryRequestDto;
import com.fortune.fortune.dto.FortuneDto;
import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.security.UserDetailsImpl;
import com.fortune.fortune.service.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

    private final FortuneService fortuneService;

    @Autowired
    public FortuneController(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    // 운세 선택 -> 생년월일로 띠, 별자리 정보 확인
    @GetMapping("/api/user/fortune/select")
    public String fortuneChoice(){
        
        return "/api/user/fortune";
    }


    //랜덤 운세 불러오기
    @GetMapping("/api/user/fortune")
    public Fortune showFortune(@RequestBody FortuneDto requestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        Fortune fortune = fortuneService.showFortune();
        System.out.println(fortune);
        return fortune;
    }

}
