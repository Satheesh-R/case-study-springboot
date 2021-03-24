package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.CustomerDetails;
import com.cognizant.portal.model.CustomerPersonalDetails;
import com.cognizant.portal.model.Message;
import com.cognizant.portal.model.Quotes;


@FeignClient(name = "${quotes.feign.client.name}", url = "${quotes.feign.client.url}")
public interface QuotesProxy {
	@PostMapping("/getQuote")
	public ResponseEntity<Quotes> getQuote(@RequestBody CustomerDetails customerDetails,
			@RequestHeader("Authorization")String token);
	@PostMapping("/saveQuote")
	public ResponseEntity<Message> saveQuote(@RequestBody CustomerPersonalDetails customerPersonalDetails,
			@RequestHeader("Authorization") String token);
}