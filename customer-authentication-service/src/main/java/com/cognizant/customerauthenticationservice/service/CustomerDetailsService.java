package com.cognizant.customerauthenticationservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.customerauthenticationservice.model.CustomerDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("INSIDE LOAD USER BY USERNAME");
		CustomerDetails customerDetails = customerService.findByUsername(username);
		log.info("END LOAD USER BY USERNAME");
		return new User(customerDetails.getCustomerId(), customerDetails.getPassword(), new ArrayList<>());
	}
	
	
}
