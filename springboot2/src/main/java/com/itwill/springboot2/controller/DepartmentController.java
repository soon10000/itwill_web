package com.itwill.springboot2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
	
	private final DepartmentService deptSvc;
	
	@GetMapping("list")
	public void list(Model model) {
		log.info("deptList()");
		
		List<Department> dept = deptSvc.deptListAll();
		model.addAttribute("depts", dept);
	}
	
	@GetMapping("/details/{id}")
	public String deptDetails(@PathVariable(name="id") int id, Model model) {
		log.info("deptDetails()");
		
		Department dept = deptSvc.deptReadById(id);
		model.addAttribute("dept", dept);
		
		return "/department/details";
	}

}
