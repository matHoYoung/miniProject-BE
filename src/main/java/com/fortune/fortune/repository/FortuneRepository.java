package com.fortune.fortune.repository;

import com.fortune.fortune.model.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
    long count();
    Fortune findAllById(Long longId);
}
