package com.itwill.spring02.repository;

import java.util.List;

// 매퍼 xml 파일과 연결
public interface CommentDao {
	// 아규먼트가 2개 이상일 경우 객체를 아규먼트로 전달하면 됨(ex. comment)
	// 해당 포스트에 작성되어 있는 모든 댓글을 검색하는 기능.
	List<Comment> selectByPostId(Integer postId);
	
	// 해당 포스트에 새로운 댓글을 추가하는 기능
	int insert(Comment comment);
	
	// 댓글 내용, 수정 시간을 업데이트 하는 기능
	int update(Comment comment);
	
	// 댓글 아이디로 삭제
	int deleteById(Integer id);

	// 포스트에 달려 있는 모든 댓글을 삭제.
	int deleteByPostId(Integer postId);
	
	// 포스트에 달려 있는 댓글 개수 검색
	Integer selectCommentCount(Integer postId);
	
	// 댓글 아이디(PK)로 검색
	Comment selectById(Integer id);
	
}
