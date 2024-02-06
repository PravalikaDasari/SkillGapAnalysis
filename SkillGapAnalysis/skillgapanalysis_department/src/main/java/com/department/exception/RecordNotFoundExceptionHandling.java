package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RecordNotFoundExceptionHandling{
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<String> exception(RecordNotFoundException notFoundException)
	{
		log.error("RecordNotFoundWithThisId-"+notFoundException.getMessage(),notFoundException);
		return new ResponseEntity<>(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
