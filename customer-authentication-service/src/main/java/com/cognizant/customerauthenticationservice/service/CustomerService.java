package com.cognizant.customerauthenticationservice.service;

import com.cognizant.customerauthenticationservice.model.AuthenticationRequest;
import com.cognizant.customerauthenticationservice.model.AuthenticationResponse;
import com.cognizant.customerauthenticationservice.model.CustomerDetails;
import com.cognizant.customerauthenticationservice.model.ValidationResponse;

public interface CustomerService {
	CustomerDetails findByUsername(String customerId);
	AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
	ValidationResponse validate(final String token);
}
