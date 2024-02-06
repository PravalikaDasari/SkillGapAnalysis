package com.training.exception;

public class NullPointerException extends Exception {

	private static final long serialVersionUID = 1L;

	public NullPointerException(String message) {
		super(message);
	}

	public NullPointerException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
