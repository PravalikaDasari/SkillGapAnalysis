package com.manager.exception;

public class NullPointerException extends Exception{

	private static final long serialVersionUID = 1L;

	public NullPointerException(String message, Exception exception) {
		super(message, exception);
	}
	public NullPointerException(String message) {
		super(message);
	}

}
