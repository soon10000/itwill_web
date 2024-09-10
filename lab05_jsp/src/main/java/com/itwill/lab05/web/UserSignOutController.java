package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userSignOutController", urlPatterns = {"/user/signout"})
public class UserSignOutController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("로그아웃 실행(doGet)");
		
		// 로그아웃:
		//	(1) 세션에 저장된 signedInUser(로그인 정보)를 삭제.
		//	(2)	세션 객체를 무효화(invalidate) - 세션 삭제. (2)만 실행하면 (1)은 자동으로 실행됨.
		HttpSession session = req.getSession();
		session.removeAttribute("signedInUser"); // 아규먼트: setAttribute에서 사용한 속성 이름 // 이는 (1)을 실행한거와 같다.
		
		session.invalidate(); // (2)번을 실행한 것.
		
		// 로그아웃 이후 로그인 페이지 이동
		String url = req.getContextPath() + "/user/signin";
		resp.sendRedirect(url);
		log.debug("로그아웃 성공");
	}
}
