package com.itwill.spring02.repository;

import java.security.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class PostDaoTest {
	
	@Autowired // 의존성 주입(DI)
	private PostDao postDao;
	
//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(postDao);
		
		List<Post> list = postDao.selectOrderByIdDesc();
		for (Post p : list) {
			System.out.println("\t" + p);
		}
	}
	
//	@Test
	public void testSelectById() {
		Post post1 = postDao.selectById(11); // DB 테이블에 id가 있는 경우.
		Assertions.assertNotNull(post1);
		log.debug(post1.toString());
		
		
		Post post2 = postDao.selectById(100); // DB 테이블에 id가 없는 경우
		Assertions.assertNull(post2);
	}
	
//	@Test
	public void testInsert() {
		// insert 할 데이터
		Post post = Post.builder().title("MyBatis테스트").content("MyBatis-Spring insert Test").author("admin").build();
		int result = postDao.insertPost(post); 
		Assertions.assertEquals(1, result); // result의 값이 1이면 true 아니면 false
	}
	
//	@Test
	public void testUpdate() {
		// 업데이트 할 포스트 객체
		Post post = Post.builder().id(17).title("17번 수정").content("17번 내용 수정").build();
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	public void testDelete() {
		int result = postDao.deletePost(17);
		Assertions.assertEquals(1, result);
	}

}
