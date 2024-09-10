package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j @RequiredArgsConstructor @RequestMapping("/post")
public class PostController {
	
	private final PostService postSvc;
	
	@GetMapping("/list")
	public void list(@RequestParam(name="p", defaultValue = "0") int pageNo ,Model model) {
		log.info("list(pageNo={})", pageNo);
		
		// TODO: 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록 전달
		Page<PostListItemDto> list =  postSvc.read(pageNo, Sort.by("id").descending()); // Sort.by("")의 경우 정렬 기준을 정하는 것
		model.addAttribute("page", list);
		
		//pagination fragment에서 사용하기 위한 정보.
		model.addAttribute("baseUrl", "/post/list");
	}
	
	
	// @PreAuthorize("isAuthenticated()") // -> 요청 처리하기 이전에 권한을 검사하겠다. role에 상관 없이 아이디/비밀번호로만 인증한다.
	@PreAuthorize("hasRole('USER')") // -> role이 일치하는 아이디/비밀번호 인증.
	@GetMapping("/create")
	public void create() {
		log.info("create() GET");
		
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public String postCreate(PostCreateDto dto) {
		log.info("postCreate(postCreateDto = {})",dto);
		postSvc.create(dto); 
		return "redirect:/post/list";
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping({"/details", "/modify"})
	public void details(@RequestParam(name="id")Long id, Model model) {
		log.info("details(id={})", id);
		Post post = postSvc.read(id);
		model.addAttribute("post", post);
		
		//-> view 이름은, 요청 주소가 "details"인 경우에는 details.html
		//-> 요청 주소가 "modify"인 경우에는 modify.html
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id")Long id) {
		log.info("deleteId(id={})", id);
		postSvc.deleteById(id);
		return "redirect:/post/list";
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.info("update(dto={})",dto);
		postSvc.updatePost(dto);
		return "redirect:/post/details?id=" + dto.getId();
	}
	
	
	
	@GetMapping("/search")
	public String search(PostSearchRequestDto dto, Model model) {
		log.info("search(dto={})",dto);
		// TODO
		Page<PostListItemDto> result = postSvc.search(dto, Sort.by("id").descending());
		model.addAttribute("page", result);
		
		// pagination fragment에서 사용할 현재 요청 주소 정보
		model.addAttribute("baseUrl", "/post/search");
		
		return "post/list";
	}
	


}
