package com.cognizant.customerauthenticationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class MessageTest {
	private Message responseOne = new Message();
	Message responseTwo = new Message(HttpStatus.OK, LocalDateTime.MAX, "Testing");

	@Test
	public void testStatus() {
		responseOne.setStatus(HttpStatus.OK);
		assertEquals(responseOne.getStatus(), HttpStatus.OK);
	}

	@Test
	public void testDate() {
		responseOne.setTimestamp(LocalDateTime.MAX);
		assertEquals(responseOne.getTimestamp(), LocalDateTime.MAX);
	}

	@Test
	public void testMsg() {
		responseOne.setMessage("Testing");
		assertEquals(responseOne.getMessage(), "Testing");
	}
}