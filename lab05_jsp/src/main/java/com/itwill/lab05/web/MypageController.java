package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet(name="mypageController", urlPatterns = {"/user/mypage"})
public class MypageController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(MypageController.class);
	private static UserService userService = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("마이페이지 이동(doGet)");
		
		String userid = req.getParameter("userid");
		User user = userService.read(userid);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/views/user/mypage.jsp").forward(req, resp);
	}

}
