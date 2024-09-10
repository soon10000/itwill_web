package com.itwill.springboot2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // -> final 필드를 초기화하는 생성자. 생성자에 의한 의존성 주입.
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService empSvc; // 생성자에 의한 의존성 주입: (1) @RequiredArgsConstructor + (2)final field
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		// 서비스(비즈니스) 계층의 메서드를 호출해서 데이터
		List<Employee> list = empSvc.read();
		
		model.addAttribute("employees", list); // 직원 목록을 뷰 템플릿에세 전달
	}
	
	
	
	@GetMapping("/details/{id}")
	public String enameDetails(@PathVariable(name="id") Integer id, Model model) {
		log.info("enameDetails() id={}", id);
		Employee emp = empSvc.readById(id);
		model.addAttribute("employee", emp);	
		
		return "employee/details";
	}
}
