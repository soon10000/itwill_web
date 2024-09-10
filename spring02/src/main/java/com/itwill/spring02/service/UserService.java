package com.itwill.spring02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring02.dto.UserCreateDto;
import com.itwill.spring02.repository.User;
import com.itwill.spring02.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor // 의존성 주입 중 한가지 방법 final로 객체를 선언하고 RequiredArgsConstructor 애너테이션을 삽입하여 생성자 만들기.
public class UserService {
	
	private final UserDao userDao;
	
	
	
	// 아이디 중복 체크: true - 중복되지 않은 아이디(사용 가능한 아이디), false - 중복 아이디(사용 할 수 없는 아이디)
	public boolean checkUserid(String userid) {
		log.debug("chekUserid(userid={})",userid);
		
		User user = userDao.selectByUserid(userid);
		if (user == null) { // userid 가 일치하는 레코드가 없을 경우(중복된 아이디가 없는 경우)
			return true;
		} else { // userid가 일치하는 레코드가 있을 때 (중복된 아이디가 있을 경우)
			return false;
		}
	}
	
	// 회원가입 서비스 메서드
	public int create(UserCreateDto dto) {
		log.debug("create({})", dto);
		int result = userDao.insert(dto.toEntity());
		return result;
	}

}
