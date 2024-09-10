package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.service.DepartmentService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller  @Slf4j @RequestMapping("/department") @RequiredArgsConstructor
public class DepartmentController {
	
	private final DepartmentService deptSvc;
	
	@GetMapping("/list")
	public void list(@RequestParam(name="page", defaultValue = "0")int pageNo ,Model model) {
		log.info("list()");
		Page<Department> list = deptSvc.read(pageNo, Sort.by("id"));
		model.addAttribute("page", list);
	}
	
	@GetMapping("/details/{id}")
	public String departmentDetails(@PathVariable(name="id") int id, Model model) {
		log.info("departmentDetails()");
		

		
		return "details";
	}

}
