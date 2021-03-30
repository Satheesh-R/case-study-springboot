package com.cognizant.quotesservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.quotesservice.exception.TokenInvalidException;
import com.cognizant.quotesservice.model.CustomerDetails;
import com.cognizant.quotesservice.model.CustomerPersonalDetails;
import com.cognizant.quotesservice.model.Message;
import com.cognizant.quotesservice.model.QuoteDetails;
import com.cognizant.quotesservice.model.Quotes;
import com.cognizant.quotesservice.service.QuotesService;

@RestController
public class QuotesController {

	@Autowired
	private QuotesService quotesService;

	@PostMapping("/getQuote")
	public ResponseEntity<Quotes> getQuote(@RequestBody CustomerDetails customerDetails,
			@RequestHeader("Authorization") String token) throws TokenInvalidException {
		Quotes quotes = quotesService.getQuotes(customerDetails, token);
		return new ResponseEntity<Quotes>(quotes, HttpStatus.OK);
	}

	@PostMapping("/saveQuote")
	public ResponseEntity<Message> saveQuote(@RequestBody CustomerPersonalDetails customerPersonalDetails,
			@RequestHeader("Authorization") String token) throws TokenInvalidException {
		Message message = quotesService.saveQuote(customerPersonalDetails, token);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping("/getAllQuote")
	public ResponseEntity<List<QuoteDetails>> getAllQuotes(@RequestHeader("Authorization") final String token)
			throws TokenInvalidException {
		List<QuoteDetails> quotesList = quotesService.getAllQuotesByUserid(token);
		return new ResponseEntity<List<QuoteDetails>>(quotesList, HttpStatus.OK);
	}
}