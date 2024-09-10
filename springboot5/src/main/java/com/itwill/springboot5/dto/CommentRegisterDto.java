package com.itwill.springboot5.dto;

import com.itwill.springboot5.domain.Comment;

import lombok.Data;

@Data
public class CommentRegisterDto {
	
	// Ajax에서 보내는 요청 객체의 속성(property) 값
	private Long postId;
	private String ctext;
	private String writer;
	

}
