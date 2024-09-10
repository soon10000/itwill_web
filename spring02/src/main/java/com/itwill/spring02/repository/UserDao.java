package com.itwill.spring02.repository;

public interface UserDao {
	User selectByUserid(String userid);
	Integer insert(User user);
}
