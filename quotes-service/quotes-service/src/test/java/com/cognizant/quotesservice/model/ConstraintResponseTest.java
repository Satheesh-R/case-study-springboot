package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ConstraintResponseTest {
	private String testString = "HI,Hello";
	private List<String> testList = Arrays.asList(testString.split(","));
	private ConstraintErrorResponse constraintErrorResponse = new ConstraintErrorResponse(HttpStatus.OK,LocalDateTime.MAX,testList);
	
	@Test
	public void testStatus() 
	{
		constraintErrorResponse.setHttpStatus(HttpStatus.ACCEPTED);
		assertEquals(HttpStatus.ACCEPTED,constraintErrorResponse.getHttpStatus());
	}

	@Test
	public void testDate() {
		constraintErrorResponse.setTimestamp(LocalDateTime.MIN);
		assertEquals(LocalDateTime.MIN,constraintErrorResponse.getTimestamp());
	}
	
	@Test
	public void testArrayList() {
		constraintErrorResponse.setMessage(testList);
		assertEquals(constraintErrorResponse.getMessage().get(0),testList.get(0));
	}
	
}