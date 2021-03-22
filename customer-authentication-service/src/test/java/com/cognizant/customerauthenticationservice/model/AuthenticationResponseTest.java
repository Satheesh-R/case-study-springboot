package com.cognizant.customerauthenticationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationResponseTest
{
	private AuthenticationResponse authenticationResponse1 = new AuthenticationResponse();
	private AuthenticationResponse authenticationResponse2 = new AuthenticationResponse("Hi","jwt");
	
	@Test
	public void testUsername() 
	{
		authenticationResponse1.setUsername("Hi");
		assertEquals("Hi", authenticationResponse1.getUsername());
	}
	
	@Test
	public void testJwt() 
	{
		authenticationResponse1.setJwt("Jwt");
		assertEquals("Jwt", authenticationResponse1.getJwt());
	}
	
	@Test
	public void testToString() 
	{
		String toStringResult = authenticationResponse2.toString();
		assertEquals(toStringResult, authenticationResponse2.toString());
	}
}