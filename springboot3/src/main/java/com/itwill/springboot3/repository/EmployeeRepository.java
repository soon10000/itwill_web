package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot3.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	// JPA query method 작성 방법:
	// JPA에서 약속된 키워드들과 엔터티의 필드 이름들을 사용해서
	// 메서드 이름을 (camel 표기법으로) 작성할 때 사용
	
	// select * from employees where department_id = ? 쿼리문을 실행하는 메서드
	// 리턴타입을(행이 여러개 vs 단일행이냐에 따라 List를 넣어주기
	// findBy 이후 찾을 필드 이름을 카멜 표기법으로 넣어주고 해당 조인된 필드에 선언된 찾을 값 기준을 넣어준다.
	
	List<Employee> findByDepartmentId(Integer id);
	
	
	// 이름(firstName)으로 검색:
	// select * from employees where fist_name = ?
	List<Employee> findByFirstName(String firstName);
	
	// 이름에 포함된 단어로 검색:
	// select * from employees where first_name like ?
	List<Employee> findByFirstNameContaining(String keyword);
	// -> Containing: 아규먼트에 '%' 를 사용 할 필요가 없음
	
	List<Employee> findByFirstNameLike(String keyword);
	// -> List: 아규먼트에 '%' 또는 '_' 를 사용해야함 (정확한 키워드가 일치하는지 체크)
	
	// 이름에 포함된 단어로 검색, 단어의 대/소문자 구분 없이 검색
	// select * from employees where upper(first_name) like upper(?)
	List<Employee> findByFirstNameContainingIgnoreCase(String keyword);
	
	// 이름에 포함된 단어로 검색, 단어 대/소문자 구분 없이 검색, 정렬 순서는 이름 내림차순
	// select * from employees where upper(first_name) like upper(?) order by first_name desc
	List<Employee> findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc(String keyword);
	
	
	// 급여가 어떤 값을 초과하는 직원들의 정보
	// select * from employees where salary > ?
	List<Employee> findBySalaryGreaterThan(double salary);
	
	
	// GreaterThan 혹은 LessThan은 초과 미만이다  (해당 값을 포함하지 않음)
	// 급여가 어떤 미만인 직원들의 정보
	// select * from employees where salary < ?
	List<Employee> findBySalaryLessThan(double salary);
	
	
	// 급여가 어떤 범위 안에 있는 직원들의 정보
	// select * from employees where salary between ?1 and ?2
	List<Employee> findBySalaryBetween(Double start, Double end);
	
	
	// 입사 날짜가 특정 날짜 이전인 직원들의 정보 (where hire_date < ?)
	List<Employee> findByHireDateLessThan(LocalDate date);
	
	
	// 입사 날짜가 특정 날짜 이후인 직원들의 정보 (where hire_date > ?)
	List<Employee> findByHireDateGreaterThan(LocalDate date);
	
	// 입사 날짜가 특정 날짜 범위안에 있는 직원들의 정보 (where hire_date between ?1 and 2?)
	List<Employee> findByHireDateBetween(LocalDate start, LocalDate end);
	
	// 부서 이름으로 직원 검색하기
	List<Employee> findByDepartmentDepartmentName(String name);
	
	// 근무 도시 이름으로 직원 검색
	List<Employee> findByDepartmentLocationCity(String city);
	List<Employee> findByDepartmentLocationCityIgnoreCase(String city);
	
	// 대소문자 구분 없이 성(lastName) 또는 이름(firstName)에 문자열이 포함된 직원 찾기
	List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstname, String lastName);
	
	// JPQL(Java Persistence Query Language)
	// JPA에서 사용하는 "객체지향(object-oriented)" 쿼리 문법.
	// 테이블 이름과 테이블의 컬럼 이름으로 쿼리 문장을 작성하는 것이 아니라,
	// 엔터티 객체 이름과 엔터티 필드 이름으로 쿼리를 작성하는 문법.
	// alias(별명)을 반드시 사용해야 함.
	// 엔터티 이름과 필드 이름은 대소문자를 구분.
	
	@Query("select e from Employee e " 
	+ "WHERE upper(e.firstName) like upper('%' || ?1 || '%') or upper(e.lastName) like upper('%' || ?2 || '%')")
	List<Employee> findByName(String firstName, String lastName);
	
	
//	@Query("SELECT e FROM Employee e WHERE UPPER(e.firstName) LIKE UPPER('%' || :first || '%') OR UPPER(e.lastName) LIKE UPPER('%' || :last || '%')")
	@Query("SELECT e FROM Employee e WHERE UPPER(e.firstName) LIKE UPPER('%' || :keyword || '%') OR UPPER(e.lastName) LIKE UPPER('%' || :keyword || '%')")
	List<Employee> findByName2(@Param("keyword") String name);
	
	// 부서 이름으로 검색 
	@Query("SELECT e FROM Employee e WHERE e.department.departmentName = :dname")
	List<Employee> findByDeptName(@Param("dname") String deptName);
	
	// 특정 도시(예: Seattle)에 근무하는 직원들 검색
	@Query("SELECT e FROM Employee e WHERE e.department.location.city = :city")
	List<Employee> findByCity(@Param("city") String city);
	
	
	// 특정 국가(예: Canada)에 근무하는 직원들 검색
	@Query("SELECT e FROM Employee e WHERE e.department.location.country.countryName = :country")
	List<Employee> findByCountry(@Param("country")String country);
}
