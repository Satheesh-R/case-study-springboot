package com.cognizant.customerauthenticationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.customerauthenticationservice.model.AuthenticationRequest;
import com.cognizant.customerauthenticationservice.model.CustomerDetails;
import com.cognizant.customerauthenticationservice.repository.CustomerRepository;
import com.cognizant.customerauthenticationservice.util.JwtUtil;

@SpringBootTest(classes=CustomerServiceImplTest.class)
public class CustomerServiceImplTest {
	
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private JwtUtil jwtUtil;
	@Mock
	private UserDetailsService userDetailsService;
	
	@Test
	public void testfindByUsername() {
		CustomerDetails customerDetails = new CustomerDetails("Ron","pass123","Ron","Wesley");
		when(customerRepository.findById("Ron")).thenReturn(Optional.of(customerDetails));
		assertEquals(customerDetails,customerServiceImpl.findByUsername("Ron"));
	}
	
	@Test 
	public void testValidate() {
		when(jwtUtil.validateToken("Token")).thenReturn(true);
		when(jwtUtil.extractUsername("Token")).thenReturn("agent");
		assertEquals(true, customerServiceImpl.validate("Bearer Token").getIsValid());
	}
	
	@Test
	public void testValidateFail() {
		when(jwtUtil.validateToken("Token")).thenReturn(false);
		assertEquals(false, customerServiceImpl.validate("Bearer Token").getIsValid());
	}

	@Test
	public void testAuthenticate() {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("admin","admin");
		CustomerDetails customer = new CustomerDetails("admin", "admin",null,null);
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername("admin");
		UserDetails value = new User(customer.getCustomerId(),customer.getPassword(), new ArrayList<>());
		when( userDetailsService.loadUserByUsername("admin")).thenReturn(value);
		when(jwtUtil.generateToken(loadUserByUsername)).thenReturn("token");
		assertEquals("admin",customerServiceImpl.authenticate(authenticationRequest).getUsername());
	}
	
	@Test
	public void testAuthenticateFail() {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("admin","admin");
		CustomerDetails customer = new CustomerDetails("admin", "admin",null,null);
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername("admin");
		UserDetails value = new User(customer.getCustomerId(),"admin1", new ArrayList<>());
		when( userDetailsService.loadUserByUsername("admin")).thenReturn(value);
		assertThrows(UsernameNotFoundException.class, () -> customerServiceImpl.authenticate(authenticationRequest));
	}
}
