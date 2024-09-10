package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

/*
 * Querydsl 사용:
 * 1. build.gradle 파일에 의존성과 빌드 옵션을 추가.
 * 2. 프로젝트 이름 우클릭 -> Gradle -> Refresh Gradle Project
 * 3. Window -> Show View -> Other -> Gradle Executions, Gradle Tasks 오픈.
 * 4. Gradle Tasks -> 프로젝트 -> build -> clean -> 우클릭 -> Run Greadle Tasks
 * 5. Gradle Tasks -> 프로젝트 -> build -> build -> 우클릭 -> Run Greadle Tasks
 * 6. 프로젝트 이름 우클릭 -> Gradle -> Refresh Gradle Project
 * 		build/generated/querydsl 폴더에 com.itwill.springboot5.domain 패키지가 생성
 * 		패키지에는 Q로 시작하는 클래스들이 자동으로 생성.  
 * 7. com.itwill.springboot5.repository에 인터페이스(예: PostQuerydsl) 생성 및 작성
 * 8. 인터페이스를 구현하는 클래스(PostQuerydslImpl)를 생성 및 작성
 *    (1). QuerydslRepositorySupport 클래스 상속(extends)
 *        - 상위 클래스(QuerydslRepositorySupport)가 기본 생성자를 갖고 있지 않기 때문에, 아규먼트를 갖는 super(arg)를 명시적으로 호출
 *    (2). PostQuerydsl 인터페이스를 구현
 *        - 추상 메서드의 body를 구현.
 * 9. JpaRepository를 상속받는 인터페이스(PostRepository)에서 PostQuerydsl 인터페이스를 상속.    
 *    
 */


   // 인터페이스에는 public abstract가 생략되어 있음. 

public interface PostQuerydsl {
	Post searchById(Long id);
	
	// title에 포함 된 문자열로 대소문자 구분없이 검색
	List<Post> searchByTitle(String keyword);
	
	// content에 포함 된 문자열로 대소문자 구분없이 검색
	List<Post> searchByContent(String keyword);
	
	// 제목 또는 내용에 포함된 문자열 대소문자 구분없이 검색
	List<Post> searchByTitleOrContetnt(String keyword);
	
	// 수정시간: where modofied_Time between ? and ?
	List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to);
	
	// 작성자와 제목으로 검색: where author = ? and lower(title) like ?
	List<Post> searchByAuthorAndTitle(String author, String title);
	
	// 제목 / 내용 / 제목+내용 / 작성자 검색
	List<Post> searchByCategory(PostSearchRequestDto dto);
	
	// 제목 또는 내용에 검색어들 중 한 개라도 포함되어 있는 레코드들을 검색
	List<Post> searchByKeywords(String[] keywords);
	
	// Paging 처리
	Page<Post> searchByKeywords(String[] keywords, Pageable pageable); 
	
	
	
	
	

}
