package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j @SpringBootTest
public class JpaQueryMethodTest {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void test() {
		
		List<Employee> list;
//		list = empRepo.findByDepartmentId(30);
//		list = empRepo.findByFirstName("Steven");
//		list = empRepo.findByFirstNameContaining("te");
//		list = empRepo.findByFirstNameLike("%te%");
//		list = empRepo.findByFirstNameContainingIgnoreCase("Te");
//		list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("TE");
//		list = empRepo.findBySalaryGreaterThan(10_000); // 성공
//		list = empRepo.findBySalaryLessThan(10_000); // 성공
//		list = empRepo.findBySalaryBetween(10_000., 15_000.); // 앞쪽 아규먼트가 낮은값(스타트) 뒤쪽 아규먼트가 높은값(엔드)를 넣어야 함!
//		list = empRepo.findByHireDateLessThan(LocalDate.of(2003, 1, 1)); // 날짜 형식의 아규먼트는 LocalData.of를 사용하면 입력 수치를 날짜로 변환해서 넣어줌
//		list = empRepo.findByHireDateGreaterThan(LocalDate.of(2007, 5, 21));
//		list = empRepo.findByHireDateBetween(LocalDate.of(2007, 1, 1), LocalDate.of(2007, 12, 31)); 
//		list = empRepo.findByDepartmentDepartmentName("IT");
//		list = empRepo.findByDepartmentLocationCity("Seattle"); // 대소문자 구분합니다.
//		list = empRepo.findByDepartmentLocationCityIgnoreCase("seattle"); // 대소문자 구분을 하지 않으려면 IgnoreCase를 붙여주면 됨
//		list = empRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("te", "te");
//		list = empRepo.findByName("tE", "Te");
//		list = empRepo.findByName2("Te");
//		list = empRepo.findByDeptName("IT");
//		list = empRepo.findByCity("Seattle");
		list = empRepo.findByCountry("Canada"); 
		
		list.forEach(System.out::println);
	}

}
