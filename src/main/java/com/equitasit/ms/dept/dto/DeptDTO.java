package com.equitasit.ms.dept.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class DeptDTO {
	private Integer deptno;
	
	private String dname;
	
	private String loc;
}
