package com.itwill.springboot5.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@SpringBootTest
public class MemberTest {

	@Test
	public void testEqualsAndHashCode() {
		Member member1 = Member.builder().id(1L).username("admin").password("1111").email("admin@itwill.com").build();
		
		log.info("member1 = {}", member1);
		
		Member member2 = Member.builder().id(2L).username("admin").password("2222").email("admin2222@itwill.com").build();
		
		log.info("member2 = {}", member2);
		
		assertThat(member1).isEqualTo(member2);
		// member1.equals(member2) 리턴 값이 true인지 테스트.
		// Member 엔터티에서 equalsAndHashCode Include 항목을 username에만 넣었기 때문에 비교를 username으로 하여 서로 username이 같기 때문에 true로 리턴,
	}
}
