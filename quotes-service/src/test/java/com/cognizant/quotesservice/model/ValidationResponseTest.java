package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValidationResponseTest {
	ValidationResponse validationResponse = new ValidationResponse();
	ValidationResponse validationResponse1 = new ValidationResponse("Ron", true);

	@Test
	public void testUserName() {
		validationResponse.setUsername("Agent1");
		assertEquals(validationResponse.getUsername(), "Agent1");
	}

	@Test
	public void testIsvalid() {
		validationResponse.setIsValid(true);
		assertEquals(validationResponse.getIsValid(), true);
	}
}
