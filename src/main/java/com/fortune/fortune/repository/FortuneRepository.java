package com.fortune.fortune.repository;

import com.fortune.fortune.model.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
////    Long countByUserNotAndFortuneNotIn(User user, Collection<String> fortune);
//    Page<Fortune> findAllByUserNotAndFortuneNotIn(User loginId, List<Fortune> fortuneList, PageRequest fortuneRequest);

    long count();

    Fortune findAllById(Long longId);
}
