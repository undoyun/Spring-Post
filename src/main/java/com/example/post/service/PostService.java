package com.example.post.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.post.model.Post;
import com.example.post.repository.PostRepository;
import com.example.post.repository.PostRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	// PostService 객체 생성 시점에 스프링 컨테이너가 자동으로 의존성을 주입(Dependency Injection)해준다
	private final PostRepository postRepository;

	// 글 저장
	public Post savePost(Post post) {
		post.setCreateTime(LocalDateTime.now());
		postRepository.savePost(post);
		return post;
	}

	// 글 전체 조회
	public List<Post> getAllPosts(){
    	  
         return postRepository.findAllPosts();
      }

	// 아이디로 글 조회
	public Post getPostById(Long postId) {
		Post findPost = postRepository.findPostById(postId);
		findPost.incrementViews();
		return findPost;
	}

	// 글 삭제
	public void removePost(Long postId, String password) {
		Post findPost = postRepository.findPostById(postId);
		if(findPost != null && findPost.getPassword().equals(password)) {
			postRepository.removePost(postId);
		}
	}
}
