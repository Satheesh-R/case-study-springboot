package com.cognizant.quotesservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.quotesservice.model.ValidationResponse;


@FeignClient(name = "${authentication.feign.client.name}", url = "${authentication.feign.client.url}")
public interface AuthenticationProxy {
	@GetMapping("/validate")
	public ResponseEntity<ValidationResponse> validateUser(@RequestHeader("Authorization") final String token);
}