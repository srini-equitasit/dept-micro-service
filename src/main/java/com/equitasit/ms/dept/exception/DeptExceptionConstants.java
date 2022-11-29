package com.equitasit.ms.dept.exception;

public enum DeptExceptionConstants {

    DEPT_NOT_FOUND("001");

	DeptExceptionConstants(String code) {
        value = code;
    }

    public String getValue() {
        return value;
    }

    private String value;



}
