package com.cognizant.quotesservice.service;

import java.util.List;

import com.cognizant.quotesservice.exception.TokenInvalidException;
import com.cognizant.quotesservice.model.CustomerDetails;
import com.cognizant.quotesservice.model.CustomerPersonalDetails;
import com.cognizant.quotesservice.model.Message;
import com.cognizant.quotesservice.model.QuoteDetails;
import com.cognizant.quotesservice.model.Quotes;

public interface QuotesService {
    List<QuoteDetails> getAllQuotesByUserid(final String token) throws TokenInvalidException;
	Message saveQuote(CustomerPersonalDetails customerPersonalDetails,final String token) throws TokenInvalidException;
	Quotes getQuotes(CustomerDetails customerDetails,final String token) throws TokenInvalidException;
}
