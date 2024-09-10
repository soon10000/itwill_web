package com.itwill.lab05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

// 서비스(비즈니스) 계층 싱글턴 객체.
public enum UserService {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	private final UserDao userDao = UserDao.INSTANCE;
	
	// TODO: 회원 가입에 필요한 메서드. userDao.insert() 호출.
	public int signup(User user) {
		log.debug("sign 메서드 실행");
		
		int result = userDao.insert(user);
		log.debug("signin={}", user);
		
		return result;
	}
	
	
	
	public User SignIn(String userid, String password) {
		log.debug("signIn(userid={}, password={})",userid, password);
		
		// DTO (Data Transfer Object)
		User dto = User.userBulider().userid(userid).password(password).userBuild();
		User user = userDao.selectByUserIdAndPassword(dto);
		log.debug("로그인 결과 = {}", user);
		
		return user;
	}
	
	
	public User read(String userid) {
		User user = userDao.selectId(userid);
		return user;
	}
	
	
	
	
}
