package com.cognizant.quotesservice.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.quotesservice.model.ConstraintErrorResponse;
import com.cognizant.quotesservice.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TokenInvalidException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Message> tokenInvalidException(TokenInvalidException tokenInvalidException){
		log.info("INSIDE TOKEN INVALID EXCEPTION GLOBAL HANDLER");
		log.info("END OF TOKEN INVALID EXCEPTION GLOBAL HANDLER");
		return new ResponseEntity<Message>(new Message(HttpStatus.UNAUTHORIZED, LocalDateTime.now(),
				"Please login again!,Token expired"),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(feign.RetryableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<Message> serviceUnavailableException() 
	{
		return new  ResponseEntity<Message>(new Message(HttpStatus.SERVICE_UNAVAILABLE, 
				LocalDateTime.now(), "Temporarily service unavailable"),HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> constraintValidationException(ConstraintViolationException constraintViolationException)
	{
		List<String> errorMessages = new ArrayList<String>();
		for(ConstraintViolation<?> constraintViolation:constraintViolationException.getConstraintViolations()) 
		{
			errorMessages.add(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage()+",Value given by you is "+constraintViolation.getInvalidValue());
		}
		return ResponseEntity.badRequest().body(new ConstraintErrorResponse(HttpStatus.BAD_REQUEST,LocalDateTime.now(),errorMessages));
		
	}
}
