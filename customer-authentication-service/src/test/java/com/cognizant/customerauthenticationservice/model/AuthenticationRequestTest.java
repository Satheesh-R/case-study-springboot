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
		assertEquals( "Hi",authenticationRequest2.getPassword());
	}
	
	@Test
	public void usernameTest() 
	{
		authenticationRequest2.setUsername("Hi");
		assertEquals( "Hi",authenticationRequest2.getUsername());
	}
	
	@Test
	public void toStringTest() 
	{
		String toStringTest = authenticationRequest1.toString();
		assertEquals(toStringTest, authenticationRequest1.toString());
	}
}
