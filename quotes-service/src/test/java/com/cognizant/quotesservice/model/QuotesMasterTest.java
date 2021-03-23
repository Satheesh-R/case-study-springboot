package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QuotesMasterTest {
	private QuotesMaster quotesMaster1 = new QuotesMaster();
	private QuotesMaster quotesMaster2 = new QuotesMaster(1, 10L, 20L, 1000L);
	
	@Test
	public void testQuoteId() {
		quotesMaster1.setQuoteId(10);
		assertEquals(10, quotesMaster1.getQuoteId());
	} 
	
	@Test
	public void testMinAge() {
		quotesMaster1.setMinAge(10L);
		assertEquals(10L,quotesMaster1.getMinAge());
	}
	
	@Test
	public void testMaxAge() {
		quotesMaster1.setMaxAge(20L);
		assertEquals(20L,quotesMaster1.getMaxAge());
	}
	
	@Test
	public void testQuoteAmount() {
		quotesMaster1.setQuoteAmount(1000L);
		assertEquals(1000L,quotesMaster1.getQuoteAmount());
	}
	
	@Test
	public void toStringTest() {
		String expected = quotesMaster2.toString();
		assertEquals(expected, quotesMaster2.toString());
	}
}