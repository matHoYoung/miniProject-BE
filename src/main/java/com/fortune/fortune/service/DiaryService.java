package com.fortune.fortune.service;

import com.fortune.fortune.dto.DiaryRequestDto;
import com.fortune.fortune.model.Diary;
import com.fortune.fortune.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public Diary saveDiary(DiaryRequestDto requestDto) {
        Diary diary = new Diary(requestDto.getContents());
        return diaryRepository.save(diary);
    }

    public List<Diary> showDiarys() {
        return diaryRepository.findAllByOrderByCreatedAtDesc();
    }
}
