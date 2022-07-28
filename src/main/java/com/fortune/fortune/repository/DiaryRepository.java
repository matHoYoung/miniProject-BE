package com.fortune.fortune.repository;

import com.fortune.fortune.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByUseridOrderByCreatedAtDesc(Long userId); // 작성순으로 일기 조회
}
