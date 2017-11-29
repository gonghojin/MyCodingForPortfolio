package org.hojin.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFound(NotFoundException nfe) {
	
		System.out.println(nfe.getMessage());
		return new ResponseEntity<Object>(nfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
