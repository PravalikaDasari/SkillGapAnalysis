package com.employee.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class EmployeeExceptionHandler 
{
	//using for get methods
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<String> exception(NoSuchElementException exception) {
		log.error("ResourceNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	//using for save method
	@ExceptionHandler(value =NullPointerException.class)
	public ResponseEntity<String> exception(NullPointerException exception)
	{
		log.error("NullPointerException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<String> exception(IdNotFoundException exception)
	{
		log.error("IdNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> exception(EmployeeNotFoundException exception) {
		log.error("EmployeeNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> exception(IllegalArgumentException exception) {
		log.error("IllegalArgumentException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		log.error("Exception-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
