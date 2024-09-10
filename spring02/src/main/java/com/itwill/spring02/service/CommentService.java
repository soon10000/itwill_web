package com.itwill.spring02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring02.dto.CommentCreateDto;
import com.itwill.spring02.dto.CommentItemDto;
import com.itwill.spring02.dto.CommentUpdateDto;
import com.itwill.spring02.repository.Comment;
import com.itwill.spring02.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentDao commentDao; // 생성자에 의한 의존성 주입
	
	public CommentItemDto readById(Integer id) {
		log.debug("readById({})", id);
		
		// 리포지토리 컴포넌트의 메서드를 호출해서 해당 아이디의 댓글 1개를 검색.
		Comment comment = commentDao.selectById(id);
		
		// Comment 타입을 CommentItemDto 타입으로 변환해서 리턴.
		return CommentItemDto.fromEntity(comment);
	}
	
	public List<CommentItemDto> readByPostId(Integer postId){ // CommentItemDto가 필요한 필드만 선언해둔 객체이기 때문에 해당 객체로 변환해서 리턴을 해줘야 필요한 정보만 사용가능
		log.debug("readByPostId=(postId={})", postId);
		// 리포지토리(영속성) 계층의 메서드를 호출해서 comments 테이블의 테이터를 검색
		List<Comment> list = commentDao.selectByPostId(postId);
		
		
		// Comment 타입의 list를 CommentItemDto 타입의 list로 변환해서 리턴 
//		List<CommentItemDto> result = new ArrayList<>();
//		for(Comment c : list) { // stream()이 해주는 역할
//			CommentItemDto dto = CommentItemDto.fromEntity(c);
//			result.add(dto);
//		}
//		return result;          반복문을 사용한 List 타입 변환
		
		
		
		return list.stream().map(CommentItemDto::fromEntity).toList(); // 스트림을 사용한 변환 말고 다른 변환 방법이 있는지 
		// stream() => 꺼낸다
		// map() => 괄호에 있는 메서드를 실행한다.
		// toList() => 해당 리스트로 만든다
		
		
	}
	
	
	public int create(CommentCreateDto dto) {
		log.debug("create({})", dto);
		
		//리포지토리 계층의 메서드를 호출해서 comments 테이블에 insert
		int result = commentDao.insert(dto.toEntity()); // dto.toEntity()는 Comment타입으로 변환
				
		return result;
	}
	
	public int update(CommentUpdateDto dto) { // Controller에서 dto 아규먼트를 받고 Dao 계층에 전달해줘야 하는데 Dao 계층에서 아규먼트로 받는 타입이 Comment이기 때문에 변환해서 전달
		log.debug("update({})", dto);
		
		//리포지토리 컴포넌트 메서드를 호출 comments 테이블을 업데이트. 
		int result = commentDao.update(dto.toEntity());
		return result;
	}
	
	public int deleteById(Integer id) {
		log.debug("deleteById({})", id);
		
		// 리포지토리 컴포넌트의 메서드를 호출
		int result = commentDao.deleteById(id);
		return result;
	}
	


}
