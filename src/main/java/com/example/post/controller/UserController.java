package com.example.post.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.post.model.User;
import com.example.post.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor // Lombok 어노테이션: 생성자 주입
@Slf4j
@Controller
public class UserController {

	private final UserService userService; // UserService 주입

	// 회원가입 페이지 요청 처리
	@GetMapping("register")
	public String register() {
		return "register"; // 회원가입 페이지로 이동
	}

	// 회원가입 처리
	@PostMapping("register")
	public String registerSuccess(@ModelAttribute User user) {
		log.info("user: {}", user);
		User registeredUser = userService.registerUser(user); // 서비스 레이어를 통해 사용자 등록
		log.info("registeredUser: {}", registeredUser);

		return "register_success"; // 회원가입 성공 페이지로 이동
	}

//	// ID로 회원 정보 조회 ex) /user-details/{사용자ID} -> 정보를 조회하여 -> user_detail.html
//	@GetMapping("/user-detail/{id}")
//	public String UserDetails(@PathVariable(name = "id") Long id, Model model) {
//		log.info("User ID: {}", id);
//
//		User user = userService.findById(id);
////        Optional<User> user = userService.findById(id);
//
//		model.addAttribute("user", user); // 조회된 사용자 정보를 모델에 추가
//		return "user_detail"; // 사용자 상세 정보 페이지로 이동
//	}
	
	// ID로 회원 정보 조회 ex) /user-details/{사용자ID} -> 정보를 조회하여 -> user_detail.html
	@GetMapping("/user-detail/{id}")
	public String UserDetailsV1(
			@RequestParam(name = "id") Long id, Model model) {
		log.info("User ID: {}", id);

		User user = userService.findById(id);
//        Optional<User> user = userService.findById(id);

		model.addAttribute("user", user); // 조회된 사용자 정보를 모델에 추가
		return "user_detail"; // 사용자 상세 정보 페이지로 이동
	}

//    @GetMapping("/user-details/{id}")
//    public String getUserDetails(@PathVariable("id") Long id, Model model) {
//        log.info("User ID: {}", id);
//
//        // 서비스 레이어를 통해 ID로 사용자 조회
//        User user = userService.findById(id).orElse(null);
//
//        if (user == null) {
//            log.error("User not found for ID: {}", id);
//            return "user_not_found"; // 사용자 정보가 없으면 에러 페이지로 이동
//        }
//
//        model.addAttribute("user", user); // 조회된 사용자 정보를 모델에 추가
//        return "user_detail"; // 사용자 상세 정보 페이지로 이동
//    }

	@GetMapping("user-list")
	public String userList(Model model) {
		List<User> user = userService.findAll();
		model.addAttribute("users", user);

		return "user_list"; // 회원가입 페이지로 이동
	}

//	@DeleteMapping("/delete/{id}")
//	public String deleteUser(@PathVariable("id") Long id, Model model) {
//	    log.info("Delete ID: {}", id);
//	    User deletedUser = userService.deleteById(id);
//
//	    model.addAttribute("message", deletedUser);
//
//	    return "user_list"; // 삭제 후 사용자 목록 페이지로 리다이렉트
//	}
	
	


}