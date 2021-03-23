package com.cognizant.quotesservice.config;

import org.junit.jupiter.api.Test;

public class SwaggerConfigTest {
	private SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
	@Test
	public void config() 
	{
		swaggerConfiguration.apiEndPointInfo();
	}
}
