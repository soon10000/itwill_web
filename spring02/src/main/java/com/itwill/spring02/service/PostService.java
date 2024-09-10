package com.itwill.spring02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring02.dto.PostCreateDto;
import com.itwill.spring02.dto.PostListDto;
import com.itwill.spring02.dto.PostSearchDto;
import com.itwill.spring02.dto.PostUpdateDto;
import com.itwill.spring02.repository.Post;
import com.itwill.spring02.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드들을 초기화하는 아규먼트를 갖는 생성자.
@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록.
public class PostService {
	
//	@Autowired // 애너테이션을 사용한 의존성 주입 (DI: Dependency Injection)
//	private PostDao postDao;

	// 생성자에 의한 의존성 주입
	//   (1) final 필드 선언.
	//	 (2) final 필드를 초기화하는 생성자 작성
	private final PostDao postDao;
//	public PostService(PostDao postDao) {
//		this.postDao = postDao;
//	}
	

	public List<PostListDto> read(){
		log.debug("read()");
		
		List<Post> list = postDao.selectOrderByIdDesc();
//		List<PostListDto> result = new ArrayList<PostListDto>();
//		for(Post p : list) {
//			result.add(PostListDto.fromEntity(p));
//		}
		
		
		return list.stream().map(PostListDto::fromEntity).toList(); // map((x) -> PostListDto.fromEntity(x));
	}
	
	public Post selectId(Integer id) {
		log.debug("selectId");
		Post post = postDao.selectById(id);
		return post;
	}
	
	public int create(PostCreateDto dto){
		log.debug("create({})", dto);
		int result = postDao.insertPost(dto.toEntity());
		log.debug("insert 결과 = {}", result);
		
		return result;
	}
	
	public int delete(int id) {
		log.debug("delete(id={})", id);
		
		// 리포지토리 컴포넌트의 메서드를 호출(PostDao)해서 delete 쿼리를 실행.
		int result = postDao.deletePost(id);
		log.debug("delete 결과 = {}", result);
		
		return result;
	}
	
	public int update(PostUpdateDto dto) {
		int result = postDao.updatePost(dto.toEntity());
		log.debug("업데이트 결과 = {}", result);
		
		return result;
	}
	
	public List<PostListDto> search(PostSearchDto dto){
		log.debug("search({})", dto);
		List<Post> list = postDao.search(dto);
		
		return list.stream().map(PostListDto::fromEntity).toList(); // Post들을 PostList들로 변환함. // 다른 방식은 어떤게 있는지 물어보기
	}
	
}
