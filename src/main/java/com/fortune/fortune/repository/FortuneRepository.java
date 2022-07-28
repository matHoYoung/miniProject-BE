package com.fortune.fortune.repository;

import com.fortune.fortune.model.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
    long count(); // DB에 저장된 운세 전체 개수 불러오기
    Fortune findAllById(Long longId); // 랜덤하게 운세 불러오기
}
