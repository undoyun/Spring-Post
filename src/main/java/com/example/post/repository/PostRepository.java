package com.example.post.repository;

import java.util.List;

import com.example.post.model.Post;


public interface PostRepository {
   // 글 등록
   void savePost(Post post);
   
   // 글 전체 조회
   List<Post> findAllPosts();
   
   // 아이디로 글 조회
   Post findPostById(Long postId);
   
   // 글 수정
   void updatePost(Post updatePost);
   
   // 글 삭제
   void removePost(Long postId);
}