package com.cognizant.customerauthenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.customerauthenticationservice.model.AuthenticationRequest;
import com.cognizant.customerauthenticationservice.model.AuthenticationResponse;
import com.cognizant.customerauthenticationservice.model.CustomerDetails;
import com.cognizant.customerauthenticationservice.model.Message;
import com.cognizant.customerauthenticationservice.model.ValidationResponse;
import com.cognizant.customerauthenticationservice.service.CustomerServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthenticationController 
{
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@ApiOperation(value = "Health check end point",response = ResponseEntity.class)
	@GetMapping(path = "/health")
	public ResponseEntity<String> healthCheckup() 
	{
		log.info("INSIDE HEALTH CHECK");
		log.info("END OF HEALTH CHECK");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Authenticate the user",response = ResponseEntity.class,notes="Authenticate the user")
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest)
	{
		log.info("INSIDE AUTHENTICATE");
		AuthenticationResponse authenticationResponse = customerServiceImpl.authenticate(authenticationRequest);
		ResponseEntity<AuthenticationResponse> response = new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
		log.info("END OF AUTHENITCATE");
		return response;
	}
	
	@ApiOperation(value = "Validate the user",response = ResponseEntity.class,notes="Validate the user")
	@GetMapping("/validate")
	public ResponseEntity<ValidationResponse> validateUser(@RequestHeader("Authorization") final String token)
	{
		log.info("INSIDE VALIDATE");
		ValidationResponse validationResponse = customerServiceImpl.validate(token);
		ResponseEntity<ValidationResponse> response = new ResponseEntity<ValidationResponse>(validationResponse,HttpStatus.OK);
		log.info("END OF VALIDATE");
		return response;
	}
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<Message> registerCustomer(@RequestBody CustomerDetails customerDetails){
		log.info("INSIDE REGISTER CUSTOMER");
		Message message = customerServiceImpl.addCustomer(customerDetails);
		ResponseEntity<Message> response = new ResponseEntity<Message>(message,HttpStatus.OK);
		log.info("END OF REGISTER CUSTOMER");
		return response;
	}
}