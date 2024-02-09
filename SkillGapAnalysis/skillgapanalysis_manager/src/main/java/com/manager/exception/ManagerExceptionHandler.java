package com.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ManagerExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> exception(NullPointerException exception) {
		log.error("NullPointerException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
   
   @ExceptionHandler(value = ManagerNotFound.class)
	public ResponseEntity<String> exception(ManagerNotFound exception) {
		log.error("ManagerNotFound-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}