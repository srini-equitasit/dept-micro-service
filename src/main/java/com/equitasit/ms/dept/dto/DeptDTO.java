package com.equitasit.ms.dept.dto;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DeptDTO {
	private Integer deptno;
	
	private String dname;
	
	private String loc;
}
