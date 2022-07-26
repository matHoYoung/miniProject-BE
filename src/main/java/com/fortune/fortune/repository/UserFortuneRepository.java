package com.fortune.fortune.repository;

import com.fortune.fortune.model.UserFortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFortuneRepository extends JpaRepository<UserFortune, Long> {


}
