package com.cognizant.customerauthenticationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.customerauthenticationservice.model.CustomerDetails;

@SpringBootTest(classes = CustomerDetailsServiceTest.class)
public class CustomerDetailsServiceTest {
	
	@InjectMocks
	private CustomerDetailsService customerDetailsService;
	
	@Mock
	private CustomerService customerService;
	
	@Test
	public void testLoadUserName() {
		CustomerDetails customerDetails = new CustomerDetails("Harry","pass123","Harry","Potter");
		UserDetails value = new User(customerDetails.getCustomerId(),customerDetails.getPassword(), new ArrayList<>());
		when(customerService.findByUsername("Harry")).thenReturn(customerDetails);
		assertEquals(value,customerDetailsService.loadUserByUsername("Harry"));
		
	}
}
