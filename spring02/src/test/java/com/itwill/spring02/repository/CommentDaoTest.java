package com.itwill.spring02.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // 서버 없이 스프링 컨텍스트들을 읽어들일 수 있는 클래스 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class CommentDaoTest {
	
	@Autowired // 스프링 컨테이너가 생성/관리하는 빈을 주입 받음
	private CommentDao commentDao;
	
//	@Test
	public void testSelect() {
		Assertions.assertNotNull(commentDao);
		
		List<Comment> list = commentDao.selectByPostId(43);
		for (Comment c : list) {
			log.debug(c.toString());
		}
		
	}
	
//	@Test
	public void testInsert() {
		
		Comment comment = Comment.builder().postId(43).username("444").ctext("입력 333").build();
		int result = commentDao.insert(comment);
		
		Assertions.assertEquals(1, result);
	}
	
//	@Test // 완료
	public void testUpdate() {
		Comment comment = Comment.builder().id(1).ctext("댓글 업데이트 실험중 내가 하는거임").build();
		int result = commentDao.update(comment);
		
		Assertions.assertEquals(1, result);
	}
	
//	@Test // 완료
	public void testDeleteById() {
		int result = commentDao.deleteById(1);
		
		Assertions.assertEquals(1, result);
	}
	
//	@Test // 완료
	public void testDeleteByPostId() {
		int result = commentDao.deleteByPostId(43);
		Assertions.assertEquals(0, result);
	}
	
//	@Test // 완료
	public void testCount() {
		int result = commentDao.selectCommentCount(43);
		log.debug("43번 postId의 댓글 수 = {}", result);
		Assertions.assertEquals(4, result);
	}
	
	@Test // 완료
	public void testSelectById() {
		Comment comment = commentDao.selectById(4);
		log.debug("selectById=({})",comment);
		Assertions.assertNotNull(comment);
		
		Comment comment1 = commentDao.selectById(1);
		Assertions.assertNull(comment1);
		log.debug("selectById=({})",comment1);
	}
	
	

}
