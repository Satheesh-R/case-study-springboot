package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerDetailsTest {
	private CustomerDetails customerDetails1 = new CustomerDetails();
	private CustomerDetails customerDetails2 = new CustomerDetails(20L, "NO", "NO");
	
	@Test
	public void testAge() {
		customerDetails1.setAge(20L);
		assertEquals(20L, customerDetails1.getAge());
	}
	
	@Test
	public void testDrinker() {
		customerDetails1.setDrinker("NO");
		assertEquals("NO", customerDetails1.getDrinker());
	}
	
	@Test
	public void testSmoker() {
		customerDetails1.setSmoker("NO");
		assertEquals("NO", customerDetails1.getSmoker());
	}
	
	@Test
	public void testToString() {
		String toStringResult =  customerDetails2.toString();
		assertEquals(toStringResult, customerDetails2.toString());
	}
}