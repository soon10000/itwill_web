package com.itwill.spring02.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring02.dto.UserCreateDto;
import com.itwill.spring02.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup") // GET방식의 /user/signup 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()");
	}
	
	
	@PostMapping("/signup") // POST 방식의 /user/signup 요청을 처리하는 컨트롤러(회원가입)
	public String signup(UserCreateDto dto) {
		log.debug("POST signup({})", dto);
		
		userService.create(dto);
		
		return "redirect:/"; // 홈페이지로 이동 or 로그인 페이지로 이동
	}
	
	
	
	// 사용자 아이디 중복 체크 REST 컨트롤러 (view로 리턴이 아닌, 데이터의 값을 직접 반환하는 컨트롤러)
	@GetMapping("/checkid")
	@ResponseBody // 메서드 리턴 값이 클라이언트로 전달되는 데이터. (몇가지만 REST 컨트롤러를 사용하고자 하는 경우 메서드 앞에 ResponseBody 애너테이션을 삽입해주면 된다.
	public ResponseEntity<String> checkId(@RequestParam(name="userid") String userid){
		log.debug("checkId(userid={})", userid);
		
		boolean result = userService.checkUserid(userid);
		if(result) {
			return ResponseEntity.ok("Y");
		} else {
			return ResponseEntity.ok("N");
		}
	}
	
	
	
	
}
