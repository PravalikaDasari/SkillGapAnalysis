package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class ObjectEmptyExceptionHandling {
	@ExceptionHandler(value = ObjectEmptyException.class)
	public ResponseEntity<String> exception(ObjectEmptyException emptyException)
	{
		log.error("ObjectEmptyException-"+emptyException.getMessage(),emptyException);
		return new ResponseEntity<>(emptyException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
