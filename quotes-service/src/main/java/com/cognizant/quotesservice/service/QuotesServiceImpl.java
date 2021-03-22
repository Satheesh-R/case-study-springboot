package com.cognizant.quotesservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cognizant.quotesservice.exception.TokenInvalidException;
import com.cognizant.quotesservice.feign.AuthenticationProxy;
import com.cognizant.quotesservice.model.CustomerDetails;
import com.cognizant.quotesservice.model.CustomerPersonalDetails;
import com.cognizant.quotesservice.model.Message;
import com.cognizant.quotesservice.model.QuoteDetails;
import com.cognizant.quotesservice.model.Quotes;
import com.cognizant.quotesservice.repository.QuotesMasterRepository;
import com.cognizant.quotesservice.repository.QuotesRepository;
import com.cognizant.quotesservice.util.JwtUtil;

@Service
public class QuotesServiceImpl implements QuotesService {
	
	@Autowired
	private QuotesMasterRepository quotesMasterRepository;
	@Autowired
	private QuotesRepository quotesRepository;
	@Autowired
	private AuthenticationProxy authenticationProxy;
	@Autowired
	private JwtUtil jwtUtil;
	
	public Quotes getQuotes(CustomerDetails customerDetails,final String token) throws TokenInvalidException {
		boolean isValid = authenticationProxy.validateUser(token).getBody().getIsValid();
		if(isValid) {
			Long amount = quotesMasterRepository.getQuoteAmount(customerDetails.getAge());
			double finalAmount;
			if(customerDetails.getSmoker().equals("YES") & customerDetails.getDrinker().equals("NO")) {
				finalAmount = amount - (amount * 0.2);
			}
			else if(customerDetails.getSmoker().equals("NO") & customerDetails.getDrinker().equals("YES")) {
				finalAmount = amount - (amount * 0.1);
			}
			else if(customerDetails.getSmoker().equals("YES") & customerDetails.getDrinker().equals("YES")) {
				finalAmount = amount - (amount * 0.3);
			}
			else {
				finalAmount = amount;
			}
			return new Quotes((long) finalAmount);
		}
		else {
			throw new TokenInvalidException("Token expired please login again!");
		}
	}
	
	public Message saveQuote(CustomerPersonalDetails customerPersonalDetails,final String token) throws TokenInvalidException {
		boolean isValid = authenticationProxy.validateUser(token).getBody().getIsValid();
		System.out.println(token);
		if(isValid) {
			String username = jwtUtil.extractUsername(token.substring(7));
			QuoteDetails quoteDetails = new QuoteDetails(username,customerPersonalDetails.getFirstname(),
					customerPersonalDetails.getLastname(),customerPersonalDetails.getGender(),customerPersonalDetails.getAge(),
					customerPersonalDetails.getEmailid(),customerPersonalDetails.getMobileNumber(),
					customerPersonalDetails.getQuoteAmount());
			quotesRepository.save(quoteDetails);
			return new Message(HttpStatus.OK,LocalDateTime.now(),"Quote saved successfully");
			
		}
		else {
			throw new TokenInvalidException("Token expired please login again!");
		}
	}
	
	public List<QuoteDetails> getAllQuotesByUserid(final String token) throws TokenInvalidException{
		boolean isValid = authenticationProxy.validateUser(token).getBody().getIsValid();
		if(isValid) {
			String username = jwtUtil.extractUsername(token.substring(7));
			return quotesRepository.findAllByUsername(username);
		}
		else {
			throw new TokenInvalidException("Token expired please login again!");
		}
	}
}