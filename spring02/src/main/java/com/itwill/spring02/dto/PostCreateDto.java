package com.itwill.spring02.dto;

import com.itwill.spring02.repository.Post;

import lombok.Data;

@Data
public class PostCreateDto {
	// 필드 이름을 요청 파라미터 이름과 같게 선언 & 기본 생성자 & setter.
	
	private String title;
	private String content;
	private String author;
	
	public Post toEntity() { // Dao에 선언된 쿼리 메서드의 리턴값이 Post이기 떄문에
		return Post.builder().title(title).content(content).author(author).build();
	}
	
	// 요청을 받을 때 사용하는 Dto
}
