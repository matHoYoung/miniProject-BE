package com.fortune.fortune.service;

import com.fortune.fortune.dto.DiaryRequestDto;
import com.fortune.fortune.model.Diary;
import com.fortune.fortune.repository.DiaryRepository;
import com.fortune.fortune.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public Diary saveDiary(DiaryRequestDto requestDto, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String nickName = userDetails.getUser().getNickname();

        Diary diary = new Diary(requestDto, userId, nickName);

        return diaryRepository.save(diary);
    }

    public List<Diary> showDiarys(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return diaryRepository.findAllByUseridOrderByCreatedAtDesc(userId);
    }
}
