package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM (Object Relation Mapping) -> JPA(Java Persistence API) -> Hibernate
@Getter @ToString @EqualsAndHashCode
@NoArgsConstructor // 기본 생성자를 갖는 클래스를 정의
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체
@Table(name="EMP") // 클래스 이름과 실제 테이블 이름이 다를 때 실제 DB의 테이블 이름을 name으로 작성해주면 됨.
public class Employee {
	@Id // Primary Key의 컬럼 필드값을 @id 애너테이션으로 반드시 명시해줘야 함
	@Column(name="EMPNO")
	private Integer id; // 사번(EMPNO)
	
	private String ename;
	
	private String job;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY) // 여러개의 매니저 번호가 한 명을 목표로 하고 있기 때문에 매니 투 원
	@JoinColumn(name="MGR")
	private Employee manager; // 매니저가 사원 객체이기 때문에 타입을 Employee로 설정
	
	private LocalDateTime hiredate;
	
	@Column(name="SAL")
	private Double salary; // 소숫점 타입이기 때문에
	
	@Column(name="COMM")
	private Double commission;
	
	@ToString.Exclude // toString 메서더의 출력 문자열에서 제외(toString에 Department는 나오지 않게 설정)
	@ManyToOne(fetch = FetchType.LAZY) // LAZY의 경우 요청한 데이터가 조인이 필요할 때 그때 조인을 함
	@JoinColumn(name="DEPTNO") // EMP 테이블에서 DEPT 테이블과 join하는 컬럼 이름.
	private Department department;
	

}
