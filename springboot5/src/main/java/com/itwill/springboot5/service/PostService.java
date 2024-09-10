package com.itwill.springboot5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Slf4j
public class PostService {
	private final PostRepository postRepo;
	
	@Transactional(readOnly = true)
	public Page<PostListItemDto> read(int pageNo, Sort sort) {
		log.info("read(pageNo={}), sort={}", pageNo, sort);
		
		// Pageable 객체 생성
		Pageable pageable = PageRequest.of(pageNo, 5, sort); // 첫번째 아규먼트 : 현재 페이지, 두번째 아규먼트 : 한 페이지에 보여줄 개시물 수, : 세번째 아규먼트 sort 객체
		
		// TODO: 영속성(persistence/repository) 계층의 메서드를 호출해서 엔터티들의 리스트를 가져옴
		Page<Post> list = postRepo.findAll(pageable);
		log.info("page.totalPages = {}", list.getTotalPages() );
		log.info("page.number = {}", list.getNumber()); // 현재 페이지 번호
		log.info("page.hasPrevious = {}",list.hasPrevious()); // 이전 페이지가 있는 지 여부
		log.info("page.hasNxt = {}",list.hasNext()); // 다음 페이지가 있는 지 여부 확인
		
		// Page<Post> 객체를 Page<PostListItemDto> 타입으로 변환
//		Page<PostListItemDto> posts = list.map(PostListItemDto::fromEntity).toList(); // (x) -> PostListItemDto.fromEntity(x)
		
		return list.map(PostListItemDto::fromEntity);
	}
	
	
	@Transactional
	public Long create(PostCreateDto dto) {
		log.info("create(dto={})",dto);
//		Post p = Post.builder().title(dto.getTitle()).content(dto.getTitle()).author(dto.getAuthor()).build(); // 순만 작성
//		postRepo.save(p);
		
		// 영속성(repository) 계층의 메서드를 호출해서 DB insert 쿼리를 실행 
		Post entity = postRepo.save(dto.toEntity());
		log.info("entity = {}", entity);
		return entity.getId(); // DB에 insert 된 레코드의 PK(id)를 리턴. 
	}
	
	
	@Transactional(readOnly = true)
	public Post read(Long id) {
		log.info("readByid (id={})", id);
		return postRepo.findById(id).orElseThrow();
	}
	
	@Transactional
	public void deleteById(Long id) {
		log.info("delete(id={})", id);
		postRepo.deleteById(id);
	}
	
	
	@Transactional
	public void updatePost(PostUpdateDto dto) {
		log.info("updatePost(post={})", dto);
		
		Post post = postRepo.findById(dto.getId()).get();
		post.update(dto.getTitle(), dto.getContent());
//		postRepo.save(post);
		
		// @Transactional 애너테이션을 사용한 경우, DB에서 검색한 entity 객체와 데이터 값이 다르면  update 쿼리가 자동으로 실행
		// @Transactional 애너테이션을 사용하지 않은 경우 postRepo.save(post) 메서드를 직접 호출해야함.
		// 위 두가지 모두 DB에서 findbyid 메서드를 통해 객체를 가지고 와야한다.
	}
	
	@Transactional(readOnly = true)
	public Page<PostListItemDto> search(PostSearchRequestDto dto, Sort sort){
		log.info("search(dto={}, sort={})", dto, sort);
		// TODO
		Pageable pageable = PageRequest.of(dto.getP(), 5, sort);
		Page<Post> result = null; 
		
		switch(dto.getCategory()){
			case "t":
				result = postRepo.findByTitleContainingIgnoreCase(dto.getKeyword(), pageable);
				break;
			case "c":
				result = postRepo.findByContentContainingIgnoreCase(dto.getKeyword(), pageable);
				break;
			case "tc":
				result = postRepo.findByTitleOrContent(dto.getKeyword(), pageable);
				break;
			case "a":
				result = postRepo.findByAuthorContainingIgnoreCase(dto.getKeyword(), pageable);
				break;
		}
		
		
//		if(dto.getCategory().equals("t")) {
//			result = postRepo.findByTitleContainingIgnoreCase(dto.getKeyword(), pageable);
//		} else if(dto.getCategory().equals("c")) {
//			result = postRepo.findByContentContainingIgnoreCase(dto.getKeyword(), pageable);
//		} else if(dto.getCategory().equals("a")) {
//			result = postRepo.findByAuthorContainingIgnoreCase(dto.getKeyword(), pageable);
//		} else {
//			result = postRepo.findBytitleOrContent(dto.getKeyword(), pageable);
//		}
		
		
		return result.map(PostListItemDto::fromEntity);
	}

}
