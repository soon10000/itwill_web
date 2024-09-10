package com.itwill.spring02.dto;

import com.itwill.spring02.repository.Comment;

import lombok.Data;

// 댓글을 등록할 때 요청 파라미터로 전달되는 값ㅇ들을 저장하기 위한 DTO
@Data
public class CommentCreateDto { // 클라이언트에서 오는 요청에 담겨져 있는 객체 (클라이언트 -> 요청)
	
	private Integer postId;
	private String ctext;
	private String username;
	
	// CommentCreateDto 타입을 Comment 타입으로 변환해서 리턴
	public Comment toEntity() { // Entity앞에 to/from은 데이터로 보낸다 혹은 데이터로부터 받는다에 따라 상이적으로 적어주는게 구분하기 좋음.
		return Comment.builder().postId(postId).ctext(ctext).username(username).build();
	}

}
