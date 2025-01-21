package com.example.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.post.model.User;
import com.example.post.repository.UserRepository;

@Service
public class UserService {
	/*
	 * 의존성 주입 방법
	 * 1. 필드 주입
	 * 2. 생성자 주입
	 * 3. 세터 주입
	 */
	
	@Autowired
	private UserRepository userRepository;

	@Autowired // 생성자 주입
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	// 필드 주입 방법
	// 사용자 등록
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	 // ID로 사용자 조회
    public User findById(Long id) {
    	// 서비스 레이어를 통해 ID로 사용자 조회
        Optional<User> result = userRepository.findById(id);
        
    	if (result.isPresent()) {
            return result.get(); // 사용자 정보가 없으면 에러 페이지로 이동
        }
    	
        throw new RuntimeException("회원정보가 없습니다.");
    }
	
//    public List<User> findAll() {
//        List<User> result = userRepository.findAll();
//
//        List<User> userList = new ArrayList<>();
//
//        for (User user : result) {
//            userList.add(user);
//        }
//
//        return userList;
//    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

 // ID로 사용자 삭제
    public User deleteById(Long id) {
        return userRepository.deleteById(id); // 사용자 삭제
    }


}
