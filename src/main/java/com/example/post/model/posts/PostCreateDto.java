package com.example.post.model.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostCreateDto {
	@NotBlank
	@Size(min = 1, message = "제목은 1자 이상 200자 이하로 입력해 주세요")
	private String title; // 제목
	@NotBlank
	@Size(min = 1)
	private String content; // 내용

	public Post toEntity() {
		return Post.builder()
				.title(this.title)
				.content(this.content)
				.build();
	}
}
