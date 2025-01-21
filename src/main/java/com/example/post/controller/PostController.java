package com.example.post.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.post.model.Post;
import com.example.post.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
   private final PostService postService;
   
   // 게시글 작성 페이지 이동
   
   @GetMapping("posts/create")
   public String createPostForm()   {
      // 게시글 작성 페이지의 뷰 이름을 리턴
      return "posts/create"; 
   }
   
    // 게시글 등록 
    @PostMapping("posts")
    public String savePost(@ModelAttribute Post post) {
//        log.info("post: {}", post);   
        Post savedPost = postService.savePost(post);
//        log.info("savePost : {}", savedPost);
        return "redirect:/posts"; 
        //redirect를 쓰는이유는 url주소창에서 중복으로 적히게 하지않기위해 작성하는거임
    }
    
    // 게시글 목록 조회
    @GetMapping("posts")
    public String listPosts(Model model) {
    	List<Post> posts = postService.getAllPosts();
		model.addAttribute("posts", posts);
//    	log.info("allpost: {}", posts);
    	return "posts/list";
    }
    
    @GetMapping("posts/{postId}")
    public String viewPost(@PathVariable("postId") Long postId, Model model) {
        log.info("Post ID: {}", postId);
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "posts/view";
    }
    
    // 게시글 삭제
    @PostMapping("posts/remove/{postId}")
    public String removePost(@PathVariable(name = "postId") Long postId,
                             @RequestParam(name = "password") String password) {
        // 게시글 삭제 서비스 호출
        postService.removePost(postId, password);
        return "redirect:/posts";  // 삭제 후 게시글 목록 페이지로 리디렉션
    }

    


    
    
    
    
}