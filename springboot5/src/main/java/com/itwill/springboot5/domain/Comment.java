package com.itwill.springboot5.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter @ToString @EqualsAndHashCode(callSuper = true)
@Entity @Table(name = "COMMENTS")
public class Comment extends BaseTimeEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Exclude // toString 메서드를 만들 때 제외시킴
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	private Post post;
	
	@Basic(optional = false) // NOT NULL 제약조건을 알리는 애너테이션
	private String ctext;
	
	@Basic(optional = false) // NOT NULL 제약조건을 알리는 애너테이션
	private String writer;
	
	public Comment update(String ctext) {
		this.ctext = ctext;
		
		return this;
	}

}
