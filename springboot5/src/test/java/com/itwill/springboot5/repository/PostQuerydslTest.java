package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {
	@Autowired
	private PostRepository postRepo;
	
//	@Test
	public void searTest() {
		
		
		Post entity = postRepo.searchById(8L);
		log.info("searchTest(entity={})", entity);
	}
	
	@Test
	public void test() {
		List<Post> result = null;
//		result = postRepo.searchByTitle("te");
//		result = postRepo.searchByContent("테스"); 
//		result = postRepo.searchByTitleOrContetnt("33");
//		result = postRepo.searchByModifiedTime(LocalDateTime.of(2024, 07, 29, 0, 0), LocalDateTime.of(2024, 07, 29, 23, 59));
//		result = postRepo.searchByAuthorAndTitle("admin", "te");
		PostSearchRequestDto dto = new PostSearchRequestDto();		
//		dto.setCategory("tc");
//		dto.setKeyword("dum title");
//		result = postRepo.searchByCategory(dto);
		
		String[] keywords =  "dum title".split(" ");  // keyword 변수에 있는 단어를 split을 사용하여 공백 기준으로 키워드를 잘라서 keywords 변수에 저장하여라  {"dum", "title"};
//		result = postRepo.searchByKeywords(keywords);
//		result.forEach(System.out::println);
		
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
		Page<Post> page = postRepo.searchByKeywords(keywords, pageable);
		page.forEach(System.out::println);
		
//		for (int i = 0; i < 5; i++) {
//			log.info("{}",result.get(i));
//		}
		
	}
	
	
	
}
