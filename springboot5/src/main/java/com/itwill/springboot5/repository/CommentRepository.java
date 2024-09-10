package com.itwill.springboot5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	//JPA query method:
//	Page<Comment> findByPostId(Long Id, Pageable pageable); 와 동일
	Page<Comment> findByPost(Post post, Pageable pageable); 
	
}
