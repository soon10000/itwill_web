package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl{
	
	public PostQuerydslImpl() {
		super(Post.class);
	}

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id={})", id);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // select p from Post p
		query.where(post.id.eq(id)); // query + where id = ?
		Post entity = query.fetchOne();
		
		return entity;
	}
	
	@Override
	public List<Post> searchByTitle(String keyword) {
		log.info("searchByTitle=(keyword={})", keyword);
		
		QPost post = QPost.post; // QPost 객체를 생성하며 QPost. 에는 리턴 받는 타입
		JPQLQuery<Post> query = from(post); // 엔터티를 넣어줘야함 // select * from Post
		query.where(post.title.containsIgnoreCase(keyword)); // post 엔터티 title 컬럼에서 키워드가 포함되어 있고, 대소문자 구분 없게, where절
		query.orderBy(post.id.desc()); // post 엔터티에 있는 id 기준으로 desc 정렬 하겠다. (order by 절)
		
		List<Post> result = query.fetch();
		
//		JPQLQuery<Post> query = from(post).where(post.title.containsIgnoreCase(keyword)).orderBy(post.id.desc()); // 이렇게 연속적으로 이어도 가능
		
		return result;
	}
	
	
	
	@Override
	public List<Post> searchByContent(String keyword) {
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.content.containsIgnoreCase(keyword)).orderBy(post.id.desc());
		List<Post> result = query.fetch();

		return result;
	}
	
	@Override
	public List<Post> searchByTitleOrContetnt(String keyword) {
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword))).orderBy(post.id.desc());
		List<Post> result = query.fetch();
		
		return result;
	}
	
	@Override
	public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.modifiedTime.between(from, to)).orderBy(post.modifiedTime.desc());
		List<Post> result = query.fetch();
		return result;
	}
	
	@Override
	public List<Post> searchByAuthorAndTitle(String author, String title) {
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.author.eq(author).and(post.title.containsIgnoreCase(title))).orderBy(post.id.desc());
		List<Post> result = query.fetch();
		
		return result;
	}
	
	@Override
	public List<Post> searchByCategory(PostSearchRequestDto dto) {
		
		log.info("searchByCategory(dto={})", dto);
		String category = dto.getCategory();
		String keyword = dto.getKeyword();
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		// BooleanBuilder: where() 메서드의 아규먼트인 BooleanExpression 객체를 생성할 수 있는 객체.
		BooleanBuilder builder = new BooleanBuilder();
		switch(category) {
		case "t":
			builder.and(post.title.containsIgnoreCase(keyword));
			break;
		case "c":
			builder.and(post.content.containsIgnoreCase(keyword));
			break;
		case "tc":
			builder.and(post.content.containsIgnoreCase(keyword)).or(post.title.containsIgnoreCase(keyword));
			break;
		case "a":
			builder.and(post.author.containsIgnoreCase(keyword));
			break;
		}
		
		query.where(builder).orderBy(post.id.desc());
		
		return query.fetch();
	}
	
	
	@Override
	public List<Post> searchByKeywords(String[] keywords) {
		log.info("searchByKeywords(keywords={})", Arrays.asList(keywords));
				
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		
		BooleanBuilder builder = new BooleanBuilder();
		
		for(String k : keywords) {
			builder.or(post.title.containsIgnoreCase(k)
					.or(post.content.containsIgnoreCase(k)));
		}
		
		query.where(builder).orderBy(post.id.desc());
		
		return query.fetch();
	}
	
	@Override // 페이징 처리가 포함 된 메서드
	public Page<Post> searchByKeywords(String[] keywords, Pageable pageable) {
		log.info("searchByKeywords(keywords={}, Pageable={}", Arrays.asList(keywords), pageable);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for(String k : keywords) {
			builder.or(post.title.containsIgnoreCase(k)
					.or(post.content.containsIgnoreCase(k)));
		}
		query.where(builder);
		
		// Paging & Sorting 적용
		// getQuery() 메서드는 QuerydslRepositorySupport으로부터 상속받은 메서드 첫번째 아규먼트는 Pageable
		// 두번째 아규먼트는 JPQuery 타입의 객체를 넘겨주면 된다.
		getQuerydsl().applyPagination(pageable, query); 
		
		// 한 페이지에 표시할 데이터를 fetch. -> getQuerydsl().allyPagingnation() 메서드를 거치면 .fetch는 pageable에 요청한 페이지에 노출 될 개수 사이즈가 된다.
		List<Post> list = query.fetch();
		log.info("listSize={}", list.size());
		
		// 전체 레코드 개수를 fetch.
		long count = query.fetchCount();
		log.info("countSize={}", count);
		
		// Page<T> 객체를 생성.
		Page<Post> page = new PageImpl<Post>(list, pageable, count);
		

		return page;
	}

}
