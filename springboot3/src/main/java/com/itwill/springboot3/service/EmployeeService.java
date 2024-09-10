package com.itwill.springboot3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j @RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository empRepo;
	
	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 검색
	public Page<EmployeeListItemDto> read(int pageNo, Sort sort) {
		log.info("read(pageNo={}, sort={})", pageNo, sort);
		// 페이징 처리 작업 순서
		// 1. Pageable 타입의 객체를 생성: PageRequest.of(페이지 번호, 한 페이지의 아이템 개수, 정렬 기준)
		// 2. Page타입의 findAll()을 호출하여 데이터를 찾아 Page<Type>의 리스트에 저장
		// 3. Dto 타입으로 변환하여 return
		
		
		// 첫번째 파라미터는 몇번째 페이지를 보여줄지, 두번째 파라미터는 정렬을 할 수 있는
		Pageable pageable = PageRequest.of(pageNo, 10, sort);
		
		// findAll(Pageble pageable) 메서드를 호출.
		Page<Employee> page = empRepo.findAll(pageable);
		log.info("hasPrevious = {}", page.hasPrevious()); // 이전 페이지가 있는지 여부 확인
		log.info("hasNext = {}", page.hasNext()); // 다음 페이지가 있는지 여부 확인
		log.info("getTotalPages={}",page.getTotalPages()); // 전체 페이지 개수
		
		// fingAll을하여 받은 리턴 받은 Page<Employee> 타입을 Page<EmployeeListItemDto> 타입으로 변환하여 리턴. 
		// (x) -> EmployeeListItemDto.fromEntity(x)
		return page.map(EmployeeListItemDto::fromEntity);
	}
	
	
	
	@Transactional(readOnly = true)
	public Employee raed(int id){
		log.info("read(id={})", id);
		Employee emp = empRepo.findById(id).orElseThrow();
		return emp;
	}

}
