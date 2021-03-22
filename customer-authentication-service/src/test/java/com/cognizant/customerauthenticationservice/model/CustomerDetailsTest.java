package com.cognizant.customerauthenticationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerDetailsTest {
	private CustomerDetails customerDetails1 = new CustomerDetails();
	private CustomerDetails customerDetails2 = new CustomerDetails("User", "User123", "User", "User");
	
	@Test
	public void testUsername() {
		customerDetails1.setCustomerId("USER");
		assertEquals("USER", customerDetails1.getCustomerId());
	}
	
	@Test
	public void testPassword() {
		customerDetails1.setPassword("USER123");
		assertEquals("USER123", customerDetails1.getPassword());
	}
	
	@Test
	public void testFirstname() {
		customerDetails1.setFirstname("User");
		assertEquals("User", customerDetails1.getFirstname());
	}
	
	@Test
	public void testLastname() {
		customerDetails1.setLastname("UserLast");
		assertEquals("UserLast", customerDetails1.getLastname());
	}
	
	@Test
	public void testToString() {
		String toStringResult =  customerDetails2.toString();
		assertEquals(toStringResult, customerDetails2.toString());
	}
}