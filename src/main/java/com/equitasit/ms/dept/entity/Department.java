package com.equitasit.ms.dept.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "dept")
public class Department {

	@Id
	private Integer deptno;
	
	private String dname;
	
	private String loc;
}
