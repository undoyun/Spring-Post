package com.example.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.post.model.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// username 으로 회원정보 조회 -> 쿼리 메소드
	User findByUsername(String username);

}
