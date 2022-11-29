package com.equitasit.ms.dept.controller;

import java.net.InetAddress;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equitasit.ms.dept.dto.DeptDTO;
import com.equitasit.ms.dept.service.DeptService;

@RestController
@RequestMapping("/dept")
@SuppressWarnings("rawtypes")
public class DeptController {

	@Autowired
	private DeptService deptService;

	@PostMapping
	public ResponseEntity save(@RequestBody DeptDTO deptDTO) {

		ResponseEntity responseEntity = null;

		DeptDTO savedDeptDTO = deptService.save(deptDTO);

		responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(savedDeptDTO);

		return responseEntity;

	}

	@PutMapping
	public ResponseEntity update(@RequestBody DeptDTO deptDTO) {

		ResponseEntity responseEntity = null;

		DeptDTO savedDeptDTO = deptService.update(deptDTO);

		responseEntity = ResponseEntity.status(HttpStatus.OK).body(savedDeptDTO);

		return responseEntity;

	}

	@DeleteMapping("/{empId}")
	public ResponseEntity remove(@PathVariable("empId") Integer empId) {

		ResponseEntity responseEntity = null;

		deptService.remove(empId);

		responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return responseEntity;

	}

	@GetMapping("/{empId}")
	public ResponseEntity get(@PathVariable("empId") Integer empId) {

		ResponseEntity responseEntity = null;

		DeptDTO deptDTO = deptService.get(empId);
		responseEntity = ResponseEntity.status(HttpStatus.OK).body(deptDTO);

		return responseEntity;

	}

	@GetMapping
	public ResponseEntity getAll() {

		ResponseEntity responseEntity = null;

		List<DeptDTO> deptDTOList = deptService.getAll();

		responseEntity = ResponseEntity.status(HttpStatus.OK).body(deptDTOList);

		return responseEntity;

	}

	@GetMapping("test")
	public ResponseEntity test() throws Exception {
		return ResponseEntity.ok("dept test success from " + InetAddress.getLocalHost().getHostAddress());
	}

}
