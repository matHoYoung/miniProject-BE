package com.fortune.fortune.controller;

import com.fortune.fortune.dto.DiaryRequestDto;
import com.fortune.fortune.model.Diary;
import com.fortune.fortune.security.UserDetailsImpl;
import com.fortune.fortune.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    //일기 작성
    @PostMapping("/api/user/diary")
    public Diary createDiary(@RequestBody DiaryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return diaryService.saveDiary(requestDto, userDetails);
    }

    //일기 전체 조회
    @GetMapping("/api/user/list")
    public List<Diary> getDiarys(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return diaryService.showDiarys(userDetails);
    }
}
