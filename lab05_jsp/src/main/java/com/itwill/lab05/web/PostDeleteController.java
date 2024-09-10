package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postDeleteController", urlPatterns = {"/post/delete"})  // urlPatterns는 context root (http://localhost:8080/lab05)를 제외하고 그 이후 주소를 삽입
public class PostDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
	private final PostService postService = PostService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		// query string에 포함된 요청 파라미터(id) 값을 읽음
		int id = Integer.parseInt(req.getParameter("id"));
		log.debug("id={}", id);
		
		// 서비스 계층의 메서드를 호출해서 글 삭제 서비르를 실행
		postService.delete(id);
		
		
		// TODO : 목록 페이지로 이동(redirect) forword / redirect 를 선택할 때 이동되는 주소가 달라졌으면 하는 경우 redirect 처음 실행되는 URL이 유지되기를 바랄 땐 forword
		String url = req.getContextPath() + "/post/list";
		log.debug("redirect : {}", url);
		resp.sendRedirect(url);		
	}

}
