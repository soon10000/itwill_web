package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

class B{}
class A extends B {}

interface D{}
class C implements D {}

interface J {}
interface I extends J {}


/*
 * Repository<T, ID>
 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 *     |__ JpaRepository<T, ID>
 * 
 * T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입
 * 
 */




//인터페이스는 인터페이스를 상속 받을 수 있다.
// Entity 클래스명을 써주고 우측에는 해당 Entity 클래스의 PK 타입을 작성해주면 된다(String Type이면 String 기재)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
}
