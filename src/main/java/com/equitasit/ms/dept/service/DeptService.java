package com.equitasit.ms.dept.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equitasit.ms.dept.dto.DeptDTO;
import com.equitasit.ms.dept.entity.Department;
import com.equitasit.ms.dept.exception.DeptException;
import com.equitasit.ms.dept.exception.DeptExceptionConstants;
import com.equitasit.ms.dept.repository.DepartmentRepository;

@Service
public class DeptService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public DeptDTO save(DeptDTO deptDTO) {

		Department department = modelMapper.map(deptDTO, Department.class);

		Department savedDepartment = departmentRepository.save(department);

		return modelMapper.map(savedDepartment, DeptDTO.class);
	}

	public DeptDTO update(DeptDTO deptDTO) {

		Optional<Department> deptOptional = departmentRepository.findById(deptDTO.getDeptno());

		if (!deptOptional.isPresent()) {

			throw new DeptException(DeptExceptionConstants.DEPT_NOT_FOUND.getValue());
		}

		Department department = modelMapper.map(deptDTO, Department.class);

		Department savedDepartment = departmentRepository.save(department);

		return modelMapper.map(savedDepartment, DeptDTO.class);
	}

	public void remove(Integer deptNo) {

		Optional<Department> deptOptional = departmentRepository.findById(deptNo);

		if (!deptOptional.isPresent()) {

			throw new DeptException(DeptExceptionConstants.DEPT_NOT_FOUND.getValue());
		}

		departmentRepository.deleteById(deptNo);

	}

	public DeptDTO get(Integer deptno) {

		Optional<Department> deptOptional = departmentRepository.findById(deptno);

		if (!deptOptional.isPresent()) {

			throw new DeptException(DeptExceptionConstants.DEPT_NOT_FOUND.getValue());
		}

		return modelMapper.map(deptOptional.get(), DeptDTO.class);

	}

	public List<DeptDTO> getAll() {

		return departmentRepository.findAll().stream().map(dept -> modelMapper.map(dept, DeptDTO.class))
				.collect(Collectors.toList());

	}
}
