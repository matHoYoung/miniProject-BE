package com.fortune.fortune.controller;

import com.fortune.fortune.model.Fortune;
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


    @GetMapping("/api/user/fortuneChoice")
    public String fortuneChoice(){
        return "/api/user/fortune";
    }

    @GetMapping("/api/user/fortune")
    public Fortune showFortune(){
        Fortune fortune = fortuneService.showFortune();
        return fortune;
    }

}
