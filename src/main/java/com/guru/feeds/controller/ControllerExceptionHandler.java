package com.guru.feeds.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

/**
 * Global Exception Handler.
 * Applies to all Controllers
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException e) {
	    Map<String, String> errors = new HashMap<String, String>();
	    e.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public String handleNotFoundException(NotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		return e.getMessage();
	}
}
