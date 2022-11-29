package com.equitasit.ms.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equitasit.ms.dept.entity.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Integer> {

}
