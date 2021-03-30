package com.cognizant.quotesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QuotesTest {
	private Quotes quotes1 = new Quotes();
	private Quotes quotes2 = new Quotes(1000L);
	
	@Test
	public void testquoteAmount() {
		quotes1.setQuoteAmount(100L);
		assertEquals(100L, quotes1.getQuoteAmount());
	}
	
	@Test
	public void toStringTest() {
		String result = quotes2.toString();
		assertEquals(result, quotes2.toString());
	}
}