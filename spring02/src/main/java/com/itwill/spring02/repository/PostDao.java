package com.itwill.spring02.repository;

import java.util.List;

import com.itwill.spring02.dto.PostSearchDto;

public interface PostDao {

	// post-mapper.xml에서 id="selectOrderByIdDesc"인 SQL을 실행하는 메서드.
	// mapper에서 선언한 쿼리문에 필요한 변수값 수에 따라 객체를 선택해서 넘기면 된다.
	List<Post> selectOrderByIdDesc(); // 전체 목록보기
	Post selectById(Integer id);
	int insertPost(Post post); // MyBatis의 경우 아규먼트를 1개만 넘길 수 있기 때문에, 아규먼트를 여러개 넘겨야 하는 경우 객체를 넘기면 된다.
	int updatePost(Post post); // MyBatis의 경우 아규먼트를 1개만 넘길 수 있기 때문에, 아규먼트를 여러개 넘겨야 하는 경우 객체를 넘기면 된다.
	int deletePost(Integer id); 
	List<Post> search(PostSearchDto dto); // PostSearchDto에 해당되는 검색 조건에 맞췄을 때 한개가 아닌 여러개의 포스트를 리턴해줘야 하기 때문에 List<Post>로 리턴값을 설정
	                                      // search()를 만든 쿼리문에서 ?를 넣어야하는 부분이 2가지 이므로 필요한 2개의 아규먼트를 받기 위해 Dao에서 필요한 필드를 선언해서 아규먼트로 넘겨주는 형식임.
	
	
	// 요청에 따른 컨트롤러, 서비스, Dto, Dao를 작성할 때 어떤 순서로 작성하면 이해가 좋은지 순서를 알아봐라
}
