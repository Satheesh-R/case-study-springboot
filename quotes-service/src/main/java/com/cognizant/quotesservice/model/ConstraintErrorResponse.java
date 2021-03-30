package com.cognizant.quotesservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**Model class which is used to store constraint error response */
@Getter
@Setter
@AllArgsConstructor
public class ConstraintErrorResponse 
{
	/**
	*  status-contains HttpStatus
	*/
	private HttpStatus httpStatus;
	/**
	*  timestamp-contains timestamp
	*/
	private LocalDateTime timestamp;
	/**
	*  message-contains custom error messages
	*/
	private List<String> message;
}