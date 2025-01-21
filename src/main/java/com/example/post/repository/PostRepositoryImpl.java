package com.example.post.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.post.model.Post;
import com.example.post.model.User;
@Repository
public class PostRepositoryImpl implements PostRepository {

   private static Map<Long, Post> posts = new HashMap<>();
   private static long sequence = 0;
   
   
   @Override
   public void savePost(Post post) {
      post.setId(++sequence);
      posts.put(post.getId(), post);
      
   }

   @Override
   public List<Post> findAllPosts() {
      
      return new ArrayList<>(posts.values());
   }

   @Override
   public Post findPostById(Long postId) {
      return posts.get(postId);
   }
   
   @Override
   public void updatePost(Post updatePost) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void removePost(Long postId) {
	   posts.remove(postId);
      
   }

}
