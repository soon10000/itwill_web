package com.itwill.springboot5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot5.domain.Member;

import jakarta.persistence.Entity;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	// SELECT m.*, r.* 
	// FROM member m 
	// 	LEFT JOIN member_roles r
	//		ON m.id = r.member_id
	// WHERE m.username = ? 와 같은 메서드
	@EntityGraph(attributePaths = "roles")
	Optional<Member> findByUsername(String username); // 데이터가 있으면 오직 1건(유니크 제약)이면, Optional 타입으로 선언
	

}
