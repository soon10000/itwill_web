package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	
	// CommentRepository CRUD 기능을 단위 테스트.
	
	@Autowired
	private CommentRepository commentRepo;


	// 특정 postId를 포함한 코멘트 검색
//	@Test
//	public void read() {
//		List<Comment> comment = commentRepo.findByPostId(54L);
//		assertThat(comment.size()).isEqualTo(2);
//		comment.forEach(System.out::println);
//	}
	
	
//	// 새 코멘트 작성
//	@Test
//	public void testCreate() {
//		Post post = Post.builder().id(54L).build();
//		
//		Comment comment = Comment.builder().post(post).ctext("test1").writer("ttttee").build();
//		commentRepo.save(comment);
//		assertThat(comment.getId().equals(1));
//		
//	}
	
	
	// 특정 코멘트 삭제
//	@Test
	public void testDelete() {
		commentRepo.deleteById(4L);
	}
	
	// 코멘트 업데이트
	
//	@Test
	public void testUpdate() {
		Comment comment = commentRepo.findById(5L).get();
		comment.update("수정이 잘 될까요?");
		commentRepo.save(comment);
	}
	

}
