package com.department.exception;

public class ObjectEmptyException extends Exception
{
	private static final long serialVersionUID = 1L;
	public ObjectEmptyException() {
		super();
	}
	public ObjectEmptyException(String message)
	{
		super(message);
	}

}
