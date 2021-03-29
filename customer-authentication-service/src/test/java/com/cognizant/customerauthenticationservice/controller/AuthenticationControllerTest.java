package com.cognizant.customerauthenticationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognizant.customerauthenticationservice.model.AuthenticationRequest;
import com.cognizant.customerauthenticationservice.model.CustomerDetails;
import com.cognizant.customerauthenticationservice.model.Message;
import com.cognizant.customerauthenticationservice.model.ValidationResponse;
import com.cognizant.customerauthenticationservice.repository.CustomerRepository;
import com.cognizant.customerauthenticationservice.service.CustomerDetailsService;
import com.cognizant.customerauthenticationservice.service.CustomerServiceImpl;
import com.cognizant.customerauthenticationservice.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=AuthenticationControllerTest.class)
@ComponentScan(basePackages = {"com.cognizant.customerauthenticationservice"})
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	@InjectMocks
	private AuthenticationController authenticationController;
	
	@Mock
	private CustomerServiceImpl customerServiceImpl;
	
	@Mock
	private JwtUtil jwtutil;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CustomerDetailsService customerDetailsService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void testHealthCheckup() {
		ResponseEntity<?> healthCheckUp=authenticationController.healthCheckup();
		assertEquals(healthCheckUp.getStatusCodeValue(), 200);
	}

	@Test
	public void testAuthenticateUser() throws Exception 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		AuthenticationRequest authResquest=new AuthenticationRequest("Harry","pass123");
		String jsonString = objectMapper.writeValueAsString(authResquest);
		MvcResult result = mockMvc.perform(post("/authenticate").content(jsonString).contentType(MediaType.APPLICATION_JSON))
								.andReturn();
		int responseCode = result.getResponse().getStatus();
		assertEquals(200,responseCode);	
	}
	@Test
	public void testValidate() 
	{	
		ValidationResponse response=new ValidationResponse("Marvel",true);
		when(customerServiceImpl.validate("token")).thenReturn(response);
		assertEquals(200,authenticationController.validateUser("token").getStatusCodeValue());	
	}
	
	@Test
	public void testAddcustomer() {
		CustomerDetails customerDetails = new CustomerDetails("Jack", "pass123", "Jack", "Hertz","IND");
		Message message = new Message(HttpStatus.OK,LocalDateTime.now(),"User registered successfully");
		when(customerServiceImpl.addCustomer(customerDetails)).thenReturn(message);
		assertEquals(HttpStatus.OK,authenticationController.registerCustomer(customerDetails).getStatusCode());
	}
}
