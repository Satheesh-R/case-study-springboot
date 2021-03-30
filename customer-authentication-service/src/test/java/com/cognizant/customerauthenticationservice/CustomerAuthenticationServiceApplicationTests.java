package com.cognizant.customerauthenticationservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerAuthenticationServiceApplicationTests {

	@Test
	void contextLoads() {
		CustomerAuthenticationServiceApplication.main(new String[] {});
		assertTrue(true);
	}

}
