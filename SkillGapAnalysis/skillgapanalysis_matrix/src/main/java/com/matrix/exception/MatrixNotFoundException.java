package com.matrix.exception;

public class MatrixNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MatrixNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

	public MatrixNotFoundException(String message) {
		super(message);
	}
}
