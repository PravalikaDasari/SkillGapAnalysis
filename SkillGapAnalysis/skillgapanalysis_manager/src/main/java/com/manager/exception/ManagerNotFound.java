package com.manager.exception;

public class ManagerNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ManagerNotFound(String message, Exception exception) {
		super(message, exception);
	}
	public ManagerNotFound(String message) {
		super(message);
	}
}
