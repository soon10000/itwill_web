package com.itwill.springboot1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot1.dto.Author;
import com.itwill.springboot1.dto.Book;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/") // context path로 들어오는 GET 방식 요청을 처리하는 메서드
	public String home(Model model) {
		log.info("home()");
		
		LocalDateTime now = LocalDateTime.now(); // 현재시간
		model.addAttribute("currentTime", now); // 뷰에 전달하는 데이터
		
		Author author = Author.builder().firstName("찰스").lastName("다윈").build();
		Book book = Book.builder().id(1).title("종의 기원").author(author).build();
		
		log.info("book: {}", book);
		model.addAttribute("book", book);
		
		return "index"; 
		//-> 뷰의 이름을 리턴.
		//-> src/main/resources/templates/리턴값.html
	}
	
	@GetMapping("/book/list")
	public void bookList(Model model) {
		// return void인 경우 뷰의 이름은 요청주소와 동일
		log.info("bookList()");
		
		//도서 목록 더미 데이터를 저장하기 위한 리스트
		ArrayList<Book> list = new ArrayList<Book>();
		
		// 더미 데이터 5개를 리스트에 저장.
		for(int i = 1; i <= 5; i++) {
			Book book = Book.builder().id(i).title("Title-" + i).author(Author.builder().firstName("Name-" + i).lastName("LastName").build()).build();
			list.add(book);
		}
		
		Book b = Book.builder().id(10).title("종의 기원").build(); // author = null;
		list.add(b);
		
		//도서 목록을 뷰에 전달
		model.addAttribute("bookList", list);
	}
	
	@GetMapping("/book/details")
	public void bookDetails(@RequestParam(name="id")int id, Model model) {
		// TODO: 요청 파라미터 id값을 찾고, 해당 id를 갖는 book 객체를 만듦
		// 모델 Book 객체를 속성(attr)으로 저장, 뷰로 전달
		
		Book book = Book.builder().id(id).title("sunman").author(Author.builder().lastName("권").firstName("순만").build()).build();
		log.info("book: {}", book);
		model.addAttribute("book", book);
		
	}
	
	
	// path variable을 포함하는 요청을 처리하는 메서드
	// 반드시 리턴 타입은 String으로 하여 return 페이지를 설정해줘야한다.
	// 보이드로 설정을 하는 경우 path variable 값이 html이 된다(무한정으로 있어야 하는 상황)
	@GetMapping("/book/details/{id}")
	public String bookDetails2(@PathVariable(name="id") int id, Model model) {
		log.info("bookDetails2(id={})", id);
		Book book = Book.builder().id(id).title("종의 기원").author(Author.builder().firstName("유정").lastName("정").build()).build();
		model.addAttribute("book", book);
		return "book/details";
	}
	

	

}
