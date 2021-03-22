package com.cognizant.customerauthenticationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class AuthenticationRequestTest {
	private AuthenticationRequest authenticationRequest1 = new AuthenticationRequest("Ron", "pass123");
	private AuthenticationRequest authenticationRequest2 = new AuthenticationRequest();
	
	@Test
	public void passwordTest() 
	{
		authenticationRequest2.setPassword("Hi");
		assertEquals(authenticationRequest2.getPassword(), "Hi");
	}
	
	@Test
	public void usernameTest() 
	{
		authenticationRequest2.setUsername("Hi");
		assertEquals(authenticationRequest2.getUsername(), "Hi");
	}
	
	@Test
	public void toStringTest() 
	{
		String toStringTest = authenticationRequest1.toString();
		assertEquals(toStringTest, authenticationRequest1.toString());
	}
}
