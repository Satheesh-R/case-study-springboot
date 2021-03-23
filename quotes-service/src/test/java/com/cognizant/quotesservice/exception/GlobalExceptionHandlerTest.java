package com.cognizant.quotesservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {
	
	private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
	private TokenInvalidException tokenInvalidException = new TokenInvalidException(TokenInvalidExceptionMessageConstants.INVALIDMESSAGE);
	private ConstraintViolationException constraintViolationException = new ConstraintViolationException("Violation",new HashSet<ConstraintViolation<?>>());
	private ResponseEntity<?> response;
	@Test
	public void tokenInvalidExceptionTest() 
	{
		response = globalExceptionHandler.tokenInvalidException(tokenInvalidException);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
	
	@Test
	public void retyrableExceptionTest()
	{
		response = globalExceptionHandler.serviceUnavailableException();
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
	}
	
	@Test
	public void constraintViolationTest() 
	{
		List<String> errorMessages = new ArrayList<String>();
		for(ConstraintViolation<?> constraintViolation:constraintViolationException.getConstraintViolations()) 
		{
			errorMessages.add(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage()+",Value given by you is "+constraintViolation.getInvalidValue());
		}
		ResponseEntity<?> response = globalExceptionHandler.constraintValidationException(constraintViolationException);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
