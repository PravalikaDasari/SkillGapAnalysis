package com.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
