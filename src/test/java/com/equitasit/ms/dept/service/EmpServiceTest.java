package com.equitasit.ms.dept.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.equitasit.ms.dept.dto.DeptDTO;
import com.equitasit.ms.dept.entity.Department;
import com.equitasit.ms.dept.repository.DepartmentRepository;
import com.equitasit.ms.dept.service.DeptService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { DeptService.class, ModelMapper.class })
class DeptServiceTest {

	@Autowired
	private DeptService deptService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@BeforeEach
	void init() {

	}

	@Test
	void testSave() {

		DeptDTO deptDTO = getDeptDTO();

		Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(getEmployee(deptDTO));

		DeptDTO saved = deptService.save(deptDTO);

		Assertions.assertNotNull(saved);

		Assertions.assertEquals(10, saved.getDeptno());
	}

	@Test
	void testUpdate() {

		DeptDTO deptDTO = getDeptDTO();

		Mockito.when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getDepartment()));

		Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(getEmployee(deptDTO));

		DeptDTO saved = deptService.save(deptDTO);

		Assertions.assertNotNull(saved);

		Assertions.assertEquals(10, saved.getDeptno());
	}

	@Test
	void testRemove() {

		Integer deptNo = 10;

		Mockito.when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getDepartment()));

		Mockito.doNothing().when(departmentRepository).deleteById(Mockito.anyInt());

		deptService.remove(deptNo);

		Mockito.verify(departmentRepository).deleteById(Mockito.anyInt());
	}

	@Test
	void testGet() {

		Integer deptno = 10;

		Mockito.when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getDepartment()));

		DeptDTO deptDTO = deptService.get(deptno);

		Assertions.assertNotNull(deptDTO);

		Assertions.assertEquals(10, deptDTO.getDeptno());
	}

	@Test
	void testGetAll() {

		Mockito.when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getDepartment()));

		Department department = getDepartment();

		List<Department> deptList = new ArrayList<Department>();

		deptList.add(department);

		Mockito.when(departmentRepository.findAll()).thenReturn(deptList);

		List<DeptDTO> retDeptList = deptService.getAll();

		Assertions.assertNotNull(retDeptList);

		Assertions.assertTrue(!retDeptList.isEmpty());

		Assertions.assertEquals(10, retDeptList.get(0).getDeptno());
	}

	private DeptDTO getDeptDTO() {
		DeptDTO deptDTO = new DeptDTO();
		deptDTO.setDeptno(10);
		deptDTO.setDname("IT");
		return deptDTO;
	}

	private Department getDepartment() {
		Department department = new Department();
		department.setDeptno(10);
		department.setDname("IT");
		return department;

	}

	private Department getEmployee(DeptDTO deptDTO) {

		return modelMapper.map(deptDTO, Department.class);
	}

}
