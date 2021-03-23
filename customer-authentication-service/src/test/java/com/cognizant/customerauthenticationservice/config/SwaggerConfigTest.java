package com.cognizant.customerauthenticationservice.config;

import org.junit.jupiter.api.Test;


public class SwaggerConfigTest {
	private SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
	@Test
	public void config() 
	{
		swaggerConfiguration.apiEndPointInfo();
	}
}
