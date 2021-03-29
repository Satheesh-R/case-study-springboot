package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QuoteDetailsTest {
	private QuoteDetails quoteDetails1 = new QuoteDetails(); 
	private QuoteDetails quoteDetails2 = new QuoteDetails("user","user","user","Male",20,"alex@abc.com",
			9876543210L,10000L,null);
	@Test
	public void quoteIdTest() {
		quoteDetails1.setQuoteId(1L);
		assertEquals(1L, quoteDetails1.getQuoteId());
	}
	
	@Test
	public void usernameTest() {
		quoteDetails1.setUsername("user");
		assertEquals("user", quoteDetails1.getUsername());
	}
	
	@Test
	public void firstnameTest() {
		quoteDetails1.setFirstname("user");
		assertEquals("user", quoteDetails1.getFirstname());
	}
	
	@Test
	public void lastnameTest() {
		quoteDetails1.setLastname("user");
		assertEquals("user", quoteDetails1.getLastname());
	}
	
	@Test
	public void genderTest() {
		quoteDetails1.setGender("Male");
		assertEquals("Male", quoteDetails1.getGender());
	}
	
	@Test
	public void ageTest() {
		quoteDetails1.setAge(20);
		assertEquals(20, quoteDetails1.getAge());
	}
	
	@Test
	public void emailIdTest() {
		quoteDetails1.setEmailid("user@user.com");
		assertEquals("user@user.com", quoteDetails1.getEmailid());
	}
	
	@Test
	public void mobileNumberTest() {
		quoteDetails1.setMobileNumber(9876543210L);
		assertEquals(9876543210L, quoteDetails1.getMobileNumber());
	}
	
	@Test
	public void quoteAmountTest() {
		quoteDetails1.setQuoteAmount(1000L);
		assertEquals(1000L, quoteDetails1.getQuoteAmount());
	}
	
	@Test
	public void fileTest() {
		quoteDetails1.setFileData(null);
		assertEquals(null, quoteDetails1.getFileData());
	}
	
	@Test
	public void toStringTest() {
		String expected = quoteDetails2.toString();
		assertEquals(expected, quoteDetails2.toString());
	}
}
