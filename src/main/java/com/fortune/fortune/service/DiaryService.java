package com.fortune.fortune.service;

import com.fortune.fortune.dto.DiaryRequestDto;
import com.fortune.fortune.model.Diary;
import com.fortune.fortune.model.User;
import com.fortune.fortune.model.UserFortune;
import com.fortune.fortune.repository.DiaryRepository;
import com.fortune.fortune.repository.UserFortuneRepository;
import com.fortune.fortune.repository.UserRepository;
import com.fortune.fortune.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final UserFortuneRepository userFortuneRepository;

    // 일기 작성
    @Transactional
    public Diary saveDiary(DiaryRequestDto requestDto, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String nickName = userDetails.getUser().getNickname();
        UserFortune userFortune = userFortuneRepository.findByUserid(userId);
        String fortune = userFortune.getFortunecontents();

        Diary diary = new Diary(requestDto, fortune, userId, nickName);

        Optional<User> getUser = userRepository.findById(userId);
        User user = getUser.get();
        user.setCheckdiary(true); // 일기 작성시 true값으로 변경 -> 하루에 한 번만 작성가능.
        userRepository.save(user);

        return diaryRepository.save(diary);
    }

    public List<Diary> showDiarys(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return diaryRepository.findAllByUseridOrderByCreatedAtDesc(userId);
    }
}
