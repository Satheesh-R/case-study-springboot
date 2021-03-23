package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerPersonalDetailsTest {
	private CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();

	@Test
	public void testFirstName() {
		customerPersonalDetails.setFirstname("Jack");
		assertEquals("Jack", customerPersonalDetails.getFirstname());
	}
	
	@Test
	public void testLasttName() {
		customerPersonalDetails.setLastname("Hertz");
		assertEquals("Hertz", customerPersonalDetails.getLastname());
	}
	
	@Test
	public void testGender() {
		customerPersonalDetails.setGender("Male");;
		assertEquals("Male", customerPersonalDetails.getGender());
	}
	
	@Test
	public void testAge() {
		customerPersonalDetails.setAge(20);;
		assertEquals(20, customerPersonalDetails.getAge());
	}
	
	@Test
	public void testEmailId() {
		customerPersonalDetails.setEmailid("jack@abx.com");;
		assertEquals("jack@abx.com", customerPersonalDetails.getEmailid());
	}
	
	@Test
	public void testMobileNumber() {
		customerPersonalDetails.setMobileNumber(9876543210L);
		assertEquals(9876543210L, customerPersonalDetails.getMobileNumber());
	}
	
	@Test
	public void testQuoteAmount() {
		customerPersonalDetails.setQuoteAmount(1000000L);;
		assertEquals(1000000L, customerPersonalDetails.getQuoteAmount());
	}
}
