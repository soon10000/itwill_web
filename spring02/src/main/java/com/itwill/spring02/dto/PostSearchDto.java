package com.itwill.spring02.dto;

import java.util.List;

import com.itwill.spring02.repository.Post;

import lombok.Data;

@Data
// 검색 요청에서의 요청 파라미터 검색 카테고리와 검색어를 저장하기 위한 DTO
public class PostSearchDto {

	private String keyword; // 필드 이름은 파라미터 이름과 동일하게 만들어 주면 
	private String category; // 디스패쳐 서블릿이 실행되면 Dto에 있는 객체들을 모두 생성하는지? 
	
	//Entity()는 어느 상황에 만들고 사용해야하는지?

	
}
