package com.fortune.fortune.controller;

import com.fortune.fortune.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("nickname", userDetails.getUser().getNickname());
        model.addAttribute("zodiacsign", userDetails.getUser().getZodiacsign());
        model.addAttribute("starposition", userDetails.getUser().getStarposition());
        return "index";
    }
}