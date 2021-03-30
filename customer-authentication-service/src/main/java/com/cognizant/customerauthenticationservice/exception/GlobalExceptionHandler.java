package com.cognizant.customerauthenticationservice.exception;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.customerauthenticationservice.model.Message;

import lombok.extern.slf4j.Slf4j;

/**Class to handle all the exceptions*/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
	/**
	 * @param usernameNotFoundException instance of UsernameNotFoundException
	 * @return ResponseEntity which contains custom error message
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Message> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException) 
	{
		log.info("INSIDE USERNAME NOT FOUND");
		log.info("END OF USERNAME NOT FOUND");
		return new ResponseEntity<Message>(
				new Message(HttpStatus.NOT_FOUND, LocalDateTime.now(), usernameNotFoundException.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @return ResponseEntity which contains custom error message
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Message> passNotFoundException() 
	{
		log.info("INSIDE NO SUCH ELEMENT");
		log.info("END OF NO SUCH ELEMENT");
		return new ResponseEntity<Message>(
				new Message(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Wrong credentials"),
				HttpStatus.NOT_FOUND);
	}
}