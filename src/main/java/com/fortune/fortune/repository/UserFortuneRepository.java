package com.fortune.fortune.repository;

import com.fortune.fortune.model.UserFortune;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserFortuneRepository extends JpaRepository<UserFortune,Long> {
    UserFortune findByUserid(Long userId);  //오늘의 운세 userID로 저장하고 불러오기
}
