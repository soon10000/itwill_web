package com.itwill.springboot5.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass; // javax.persistence.MappedSuperclass
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode // equals(), hashcode(), canEqual() 
@ToString
@NoArgsConstructor // 기본 생성자 
@MappedSuperclass // 다른 엔터티 클래스의 상위 클래스로 상속시켜 프로젝트 내 모든 엔터티에서 사용이 가능하게끔
@EntityListeners(AuditingEntityListener.class)
// -> 엔터티 (최초) 생성시간, (최종) 수정시간 등을 자동으로 DB에 저장하기 위한 애너테이션
// @CreatedDate, @LastModifiredDate 2개 애너테이션과 묶어서 사용해야 함 
public class BaseTimeEntity {
	
	@CreatedDate // 엔터티 (최초) 생성 시간을 저장하는 필드.
	private LocalDateTime createdTime;
	
	@LastModifiedDate
	private LocalDateTime modifiedTime;

}
