package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.DepartmentRepository;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service @RequiredArgsConstructor
public class DepartmentService {
	
	private final DepartmentRepository deptRepo;
	
	private final EmployeeRepository empSvc;
	
	@Transactional(readOnly = true)
	public Page<Department> read(int pageNo, Sort sort ){
		log.info("read()");
		// 페이징 처리 작업 순서
		// 1. Pageable 타입의 객체를 생성: PageRequest.of(페이지 번호, 한 페이지의 아이템 개수, 정렬 기준)
		// 2. Page타입의 findAll()을 호출하여 데이터를 찾아 Page<Type>의 리스트에 저장
		// 3. Dto 타입으로 변환하여 return
		
		Pageable pageable = PageRequest.of(pageNo, 10, sort); 
		Page<Department> list = deptRepo.findAll(pageable);
		return list;
	}
	
//	public List<Employee> departmentFindById(int id) {
//		log.info("departmentFindById(id={})", id);
//		
//		List<Employee> list = empSvc.findAll();
//		list.stream().filter(null)
//		
//		
//		
//		return "";
//	}
	

}
