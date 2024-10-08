package com.itwill.springboot5.dto;

import java.time.LocalDateTime;

import com.itwill.springboot5.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Post Service  --> PostController 
@Data @Builder @NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListItemDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostListItemDto fromEntity(Post entity) {
		return PostListItemDto.builder().id(entity.getId()).title(entity.getTitle()).author(entity.getAuthor()).modifiedTime(entity.getModifiedTime()).build();
	}
}
