package com.itwill.spring02.dto;

import java.time.LocalDateTime;

import com.itwill.spring02.repository.Post;

import lombok.Data;

// 업데이트 요청의 요청 파라미터들을 저장하기 위한 DTO

@Data 
public class PostUpdateDto {
	private int id;
	private String title;
	private String content;
	private LocalDateTime modifiedTime;
	
	public Post toEntity() {		
		return Post.builder().id(id).title(title).content(content).modifiedTime(modifiedTime).build();
	}
}
