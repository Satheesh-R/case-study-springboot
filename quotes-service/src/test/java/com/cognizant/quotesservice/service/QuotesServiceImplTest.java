package com.cognizant.quotesservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.quotesservice.exception.TokenInvalidException;
import com.cognizant.quotesservice.feign.AuthenticationProxy;
import com.cognizant.quotesservice.model.CustomerDetails;
import com.cognizant.quotesservice.model.CustomerPersonalDetails;
import com.cognizant.quotesservice.model.QuoteDetails;
import com.cognizant.quotesservice.model.Quotes;
import com.cognizant.quotesservice.model.ValidationResponse;
import com.cognizant.quotesservice.repository.QuotesMasterRepository;
import com.cognizant.quotesservice.repository.QuotesRepository;
import com.cognizant.quotesservice.util.JwtUtil;

@SpringBootTest
public class QuotesServiceImplTest {
	
	@InjectMocks
	private QuotesServiceImpl quotesServiceImpl;
	@Mock
	private QuotesMasterRepository quotesMasterRepository;
	@Mock
	private QuotesRepository quotesRepository;
	@Mock
	private AuthenticationProxy authenticationProxy;
	@Mock
	private JwtUtil jwtUtil;
	
	@Test
	public void getQuotesTest() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "NO", "NO");
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
		Long amount = 100000000L;
		Quotes quotes = new Quotes(amount);
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		when(quotesMasterRepository.getQuoteAmount(customerDetails.getAge())).thenReturn(amount);
		assertEquals(quotesServiceImpl.getQuotes(customerDetails, "Bearer token").getQuoteAmount(), quotes.getQuoteAmount());
	}
	
	@Test
	public void getQuotesTestCondition2() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "YES", "NO");
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
		Long amount = 8100000L;
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		when(quotesMasterRepository.getQuoteAmount(customerDetails.getAge())).thenReturn(amount);
		assertEquals(quotesServiceImpl.getQuotes(customerDetails, "Bearer token").getQuoteAmount(), 7290000L);
	}
	
	@Test
	public void getQuotesTestCondition3() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "NO", "YES");
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
		Long amount = 12800000L;
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		when(quotesMasterRepository.getQuoteAmount(customerDetails.getAge())).thenReturn(amount);
		assertEquals(quotesServiceImpl.getQuotes(customerDetails, "Bearer token").getQuoteAmount(), 10240000L);
	}
	
	@Test
	public void getQuotesTestCondition4() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "YES", "YES");
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
		Long amount = 21000000L;
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		when(quotesMasterRepository.getQuoteAmount(customerDetails.getAge())).thenReturn(amount);
		assertEquals(quotesServiceImpl.getQuotes(customerDetails, "Bearer token").getQuoteAmount(), 14700000L);
	}
	
	@Test
	public void getQuotesTestCondition5() throws TokenInvalidException {
		CustomerDetails customerDetails = new CustomerDetails(10L, "NO", "NO");
		ValidationResponse validationResponse = new ValidationResponse("Harry", false);
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		assertThrows(TokenInvalidException.class, () -> quotesServiceImpl.getQuotes(customerDetails, "Bearer token"));
	}
	
	@Test
	public void saveQuoteTest() throws TokenInvalidException {
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails("Harry", "Potter", 
				"Male", 20, "harry@hogwards.com", 9876543210L, 1000L,null);
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
		QuoteDetails quoteDetails = new QuoteDetails("Harry", customerPersonalDetails.getFirstname(), 
				customerPersonalDetails.getLastname(), customerPersonalDetails.getGender(), 
				customerPersonalDetails.getAge(), customerPersonalDetails.getEmailid(), 
				customerPersonalDetails.getMobileNumber(), customerPersonalDetails.getQuoteAmount(),
				customerPersonalDetails.getFileData());
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		when(quotesRepository.save(quoteDetails)).thenReturn(quoteDetails);
		assertEquals(HttpStatus.OK,quotesServiceImpl.saveQuote(customerPersonalDetails, "Bearer token").getStatus());
	}
	
	@Test
	public void saveQuoteTestValidationFail() throws TokenInvalidException {
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails("Harry", "Potter", 
				"Male", 20, "harry@hogwards.com", 9876543210L, 1000L,null);
		ValidationResponse validationResponse = new ValidationResponse("Harry", false);
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		QuoteDetails quoteDetails = new QuoteDetails("Harry", customerPersonalDetails.getFirstname(), 
				customerPersonalDetails.getLastname(), customerPersonalDetails.getGender(), 
				customerPersonalDetails.getAge(), customerPersonalDetails.getEmailid(), 
				customerPersonalDetails.getMobileNumber(), customerPersonalDetails.getQuoteAmount(),
				customerPersonalDetails.getFileData());
		when(authenticationProxy.validateUser("Bearer Token")).thenReturn(response);
		assertThrows(TokenInvalidException.class,() -> quotesServiceImpl.saveQuote(customerPersonalDetails, "Bearer Token"));
	}
	
	@Test
	public void getAllQuotesByUserIdTest() throws TokenInvalidException {
		ValidationResponse validationResponse = new ValidationResponse("Harry", true);
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
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(quotesRepository.findAllByUsername("Harry")).thenReturn(quotesDetailsList);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		assertEquals(quotesServiceImpl.getAllQuotesByUserid("Bearer token").size(), 0);
	}
	
	@Test
	public void getAllQuotesByUserIdTestValidationFail() throws TokenInvalidException {
		ValidationResponse validationResponse = new ValidationResponse("Harry", false);
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
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse, HttpStatus.OK);
		when(quotesRepository.findAllByUsername("Harry")).thenReturn(quotesDetailsList);
		when(authenticationProxy.validateUser("Bearer token")).thenReturn(response);
		assertThrows(TokenInvalidException.class,() -> quotesServiceImpl.getAllQuotesByUserid("Bearer token"));
	}
}
