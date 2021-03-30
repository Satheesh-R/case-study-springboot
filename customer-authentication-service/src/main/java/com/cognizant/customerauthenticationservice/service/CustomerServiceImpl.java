package com.cognizant.customerauthenticationservice.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.customerauthenticationservice.model.AuthenticationRequest;
import com.cognizant.customerauthenticationservice.model.AuthenticationResponse;
import com.cognizant.customerauthenticationservice.model.CustomerDetails;
import com.cognizant.customerauthenticationservice.model.Message;
import com.cognizant.customerauthenticationservice.model.ValidationResponse;
import com.cognizant.customerauthenticationservice.repository.CustomerRepository;
import com.cognizant.customerauthenticationservice.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Override
	public CustomerDetails findByUsername(String customerId) {
		return customerRepository.findById(customerId).get();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		log.info("INSIDE AUTHENTICATE USER");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String username = null;
		String generatedToken = null;
		if(userDetails.getPassword().equals(authenticationRequest.getPassword())) 
		{
			username = authenticationRequest.getUsername();
			generatedToken = jwtUtil.generateToken(userDetails);
			log.info("END OF AUTHENTICATE USER");
			return new AuthenticationResponse(username,generatedToken);
		}
		else 
		{
			throw new UsernameNotFoundException("Wrong Credentials");
		}
	}

	@Override
	public ValidationResponse validate(String token) {
		log.info("INSIDE VALIDATE USER");
		String jwt = token.substring(7);
		ValidationResponse validationResponse = new ValidationResponse();
		if(jwtUtil.validateToken(jwt) != null && jwtUtil.validateToken(jwt)) 
		{
			validationResponse.setUsername(jwtUtil.extractUsername(jwt));
			validationResponse.setIsValid(true);
		}
		else 
		{
			validationResponse.setIsValid(false);
		}
		log.info("END OF VALIDATE USER");
		return validationResponse;
	}
	
	public Message addCustomer(CustomerDetails customerDetails) {
		log.info("INSIDE ADD CUSTOMER");
		try {
			customerRepository.findById(customerDetails.getCustomerId()).get();
		}
		catch(NoSuchElementException noSuchElementException) {
			log.info("SAVING CUSTOMER DETAILS");
			customerRepository.save(customerDetails);
			log.info("END OF ADD CUSTOMER");
			return new Message(HttpStatus.OK,LocalDateTime.now(),"User registered successfully");
		}
			log.info("USERNAME EXISTS ALREADY");
			return new Message(HttpStatus.NOT_ACCEPTABLE,LocalDateTime.now(),"Username is alreay taken or User already registered");
		}	
	}