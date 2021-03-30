package com.cognizant.quotesservice.config;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class SwaggerConfigTest {
	private SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
	@Test
	public void config() 
	{
		swaggerConfiguration.apiEndPointInfo();
		assertTrue(true);
	}
}
