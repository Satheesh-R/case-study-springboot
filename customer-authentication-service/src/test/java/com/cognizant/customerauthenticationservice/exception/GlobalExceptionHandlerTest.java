package com.cognizant.customerauthenticationservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class GlobalExceptionHandlerTest {
	private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
	private UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("User Not Found");
	
	@Test
	public void usernameNotFoundTest() 
	{
		ResponseEntity<?> response = globalExceptionHandler.usernameNotFoundException(usernameNotFoundException);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void passNotFoundTest() 
	{
		ResponseEntity<?> response = globalExceptionHandler.passNotFoundException();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}