package com.fortune.fortune.repository;

import com.fortune.fortune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // username으로 회원정보 조회

    Optional<User> findByNickname(String nickname); // nickname으로 회원정보 조회

}