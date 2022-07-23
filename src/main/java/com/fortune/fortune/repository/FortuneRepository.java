package com.fortune.fortune.repository;

import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
    Long countByUserNotAndFortuneNotIn(User loginId, List<Fortune> fortuneList);
    Page<Fortune> findAllByUserNotAndFortuneNotIn(User loginId, List<Fortune> fortuneList, PageRequest fortuneRequest);

}
