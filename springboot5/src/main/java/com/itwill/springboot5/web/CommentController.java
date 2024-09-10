package com.itwill.springboot5.web;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.dto.CommentRegisterDto;
import com.itwill.springboot5.dto.CommentUpdateDto;
import com.itwill.springboot5.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController // 레스트 방식의 컨트롤러
public class CommentController {
	
	private final CommentService commentSvc;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseEntity<Comment> registerComment(@RequestBody CommentRegisterDto dto){ // Ajax의 요청을 받는거면 @RequestBody
		log.info("registerComment(dto={})", dto);
		
		// 서비스 계층의 메서드 호출(댓글 등록 서비스 실행)
		Comment entity = commentSvc.create(dto);
		log.info("save 결과: {}", entity);
		return ResponseEntity.ok(entity);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/all/{postId}")
	public ResponseEntity<Page<Comment>> getCommentList(
			@RequestParam(name="p", defaultValue = "0") int pageNo,
			@PathVariable(name = "postId") Long postId) {
		log.info("getCommentList(postId={}, pageNo={})", postId, pageNo);
		
		Page<Comment> data = commentSvc.readCommentList(postId, pageNo);
		
		
		return ResponseEntity.ok(data);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteComment(@PathVariable(name="id") Long id) {
		log.info("deleteComment(id={})", id);
		commentSvc.delete(id);
		return ResponseEntity.ok(id); // 삭제한 댓글 아이디를 응답으로 보냄
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<Long> updateComment(@PathVariable(name="id") Long id, @RequestBody CommentUpdateDto dto) {
		log.info("updateComment(id={}, dto={})",id, dto);
		commentSvc.update(dto);
		
		return ResponseEntity.ok(id); // 업데이트한 댓글의 아이디를 응답으로 보냄.
	}
	
	// @RequestBody => Ajax에서 보낸 데이터를 받을 때
	// @RequestParam => 파라미터 값을 받을 때
	// @PathVariable => 패스베리어블을 사용하여 값을 받아올 때
	

}
