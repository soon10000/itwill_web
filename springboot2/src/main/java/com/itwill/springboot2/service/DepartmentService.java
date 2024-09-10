package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.DepartmentRepository;
import com.itwill.springboot2.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
	
	private final DepartmentRepository deptRepo;
	private final EmployeeRepository empRepo;
	
	public List<Department> deptListAll(){
		log.info("deptListAll()");
		return deptRepo.findAll();
	}
	
	public Department deptReadById(int id) {
		log.info("deptReadById(id={})", id);
		
		return deptRepo.findById(id).get();
	}

}
