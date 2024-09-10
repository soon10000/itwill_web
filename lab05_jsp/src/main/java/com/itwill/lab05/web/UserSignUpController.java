package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
	private final UserService userService = UserService.INSTANCE;
	
	// TODO: 회원 가입에 필요한 요청 처리 메서드.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		// 해당 서블릿이 작동되면 회원가입 양식 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		// req에 포함된 정보 변수에 저장
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		// 전달받은 정보로 user 객체 생성
		User user = User.userBulider().userid(userid).password(password).email(email).userBuild();
		
		// 서비스 계층 메서드 호출
		userService.signup(user);
		
		// 가입완료 이후 페이지 이동
		String url = req.getContextPath() + "/user/signin";
		log.debug("redirect:" + url);
		resp.sendRedirect(url);

	}

}
