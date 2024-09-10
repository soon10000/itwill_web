package com.itwill.spring02.dto;

import com.itwill.spring02.repository.Comment;

import lombok.Data;

@Data
public class CommentUpdateDto {
	
	private Integer id;
	private String ctext;
	
	// CommentUpdateDto 타입을 Comment 타입으로 변환해서 리턴.
	public Comment toEntity() {
		return Comment.builder().id(id).ctext(ctext).build();
	}

}
