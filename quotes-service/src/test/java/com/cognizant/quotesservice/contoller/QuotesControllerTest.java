package com.cognizant.quotesservice.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.cognizant.quotesservice.controller.QuotesController;
import com.cognizant.quotesservice.exception.TokenInvalidException;
import com.cognizant.quotesservice.model.CustomerDetails;
import com.cognizant.quotesservice.model.CustomerPersonalDetails;
import com.cognizant.quotesservice.model.Message;
import com.cognizant.quotesservice.model.QuoteDetails;
import com.cognizant.quotesservice.model.Quotes;
import com.cognizant.quotesservice.repository.QuotesMasterRepository;
import com.cognizant.quotesservice.repository.QuotesRepository;
import com.cognizant.quotesservice.service.QuotesServiceImpl;
import com.cognizant.quotesservice.util.JwtUtil;

@SpringBootTest
public class QuotesControllerTest {
	@InjectMocks
	private QuotesController quotesController;
	@Mock
	private QuotesServiceImpl quotesServiceImpl;
	@Mock
	private JwtUtil jwtUtil;
	@Mock
	private QuotesRepository quotesRepository;
	@Mock
	private QuotesMasterRepository quotesMasterRepository;
	
	@Test
	public void testGetQuote() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "NO", "NO");
		Quotes quotes = new Quotes(10000L);
		when(quotesServiceImpl.getQuotes(customerDetails, "Bearer token")).thenReturn(quotes);
		assertEquals(HttpStatus.OK, quotesController.getQuote(customerDetails, "Bearer token").getStatusCode());
	}
	
	@Test
	public void testSaveQuote() throws TokenInvalidException {
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails("Harry", "Potter", 
				"Male", 20, "harry@hogwards.com", 9876543210L, 1000L,null);
		Message message = new Message(HttpStatus.ACCEPTED,LocalDateTime.now(),"Quote Saved Successfully");
		when(quotesServiceImpl.saveQuote(customerPersonalDetails, "Bearer token")).thenReturn(message);
		assertEquals(HttpStatus.OK, quotesController.saveQuote(customerPersonalDetails, "Bearer token").getStatusCode());
	}
	
	@Test
	public void testGetAllQuotes() throws TokenInvalidException {
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails("Harry", "Potter", 
				"Male", 20, "harry@hogwards.com", 9876543210L, 1000L,null);
		QuoteDetails quoteDetails1 = new QuoteDetails("Harry", customerPersonalDetails.getFirstname(), 
				customerPersonalDetails.getLastname(), customerPersonalDetails.getGender(), 
				customerPersonalDetails.getAge(), customerPersonalDetails.getEmailid(), 
				customerPersonalDetails.getMobileNumber(), customerPersonalDetails.getQuoteAmount(),
				customerPersonalDetails.getFileData());
		QuoteDetails quoteDetails2 = new QuoteDetails("Harry", customerPersonalDetails.getFirstname(), 
				customerPersonalDetails.getLastname(), customerPersonalDetails.getGender(), 
				customerPersonalDetails.getAge(), customerPersonalDetails.getEmailid(), 
				customerPersonalDetails.getMobileNumber(), 2000L,customerPersonalDetails.getFileData());
		List<QuoteDetails> quotesDetailsList = new ArrayList<QuoteDetails>();
		quotesDetailsList.add(quoteDetails1);
		quotesDetailsList.add(quoteDetails2);
		when(quotesRepository.findAllByUsername("Harry")).thenReturn(quotesDetailsList);
		when(quotesServiceImpl.getAllQuotesByUserid("Bearer token")).thenReturn(quotesDetailsList);
		assertEquals(HttpStatus.OK, quotesController.getAllQuotes("Bearer token").getStatusCode());
	}
}