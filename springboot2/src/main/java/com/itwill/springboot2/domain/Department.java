package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity
@Table(name = "DEPT")
public class Department {
    @Id
    @Column(name = "DEPTNO")
    private Integer id;
    
    private String dname;
    
    @Column(name = "LOC")
    private String location;
    
    
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department") // OneToMany의 경우 mappedBy를 작성
    // mapperdBy: Emplyee 엔터티에서 @ManyToOne 애너테이션이 설정 된 필드 이름
    private List<Employee> employees; // OneToMany가 하나에서 여러개를 목표로 하는것이기 때문에 List<타입> 으로 선언 
    
}