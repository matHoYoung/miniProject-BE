package com.fortune.fortune.controller;

import com.fortune.fortune.model.Diary;
import com.fortune.fortune.service.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

    private final FortuneService fortuneService;

    @Autowired
    public FortuneController(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    //랜덤 운세 불러오기
    @GetMapping("/user/fortune")
    public String showFortune(){
        String fortune = fortuneService.showFortune();
        Diary diary = new Diary(fortune);
        return fortune;
    }

}
