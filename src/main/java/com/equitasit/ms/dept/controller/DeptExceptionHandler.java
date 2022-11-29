package com.equitasit.ms.dept.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.equitasit.ms.dept.dto.StatusDTO;
import com.equitasit.ms.dept.exception.DeptException;
import com.equitasit.ms.dept.exception.DeptExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class DeptExceptionHandler {

	@ExceptionHandler(value = DeptException.class)
	public ResponseEntity<StatusDTO> handleEmpException(DeptException ex) {

		log.error("Error while getting the accounts", ex);

		if (ex.getMessage().equals(DeptExceptionConstants.DEPT_NOT_FOUND.getValue())) {

			return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<StatusDTO> handleException(Exception ex) {

		log.error("Error while accessing the accounts app", ex);

		return new ResponseEntity<>(new StatusDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
