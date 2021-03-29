package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.AuthenticationRequest;
import com.cognizant.portal.model.AuthenticationResponse;
import com.cognizant.portal.model.CustomerRegistrationDetails;
import com.cognizant.portal.model.Message;
import com.cognizant.portal.model.ValidationResponse;


@FeignClient(name = "${authentication.feign.client.name}", url = "${authentication.feign.client.url}")
public interface AuthenticationProxy {
	@GetMapping("/validate")
	public ResponseEntity<ValidationResponse> validateUser(@RequestHeader("Authorization") final String token);
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest);
	@PostMapping("/registerCustomer")
	public ResponseEntity<Message> registerCustomer(@RequestBody CustomerRegistrationDetails customerDetails);
}
