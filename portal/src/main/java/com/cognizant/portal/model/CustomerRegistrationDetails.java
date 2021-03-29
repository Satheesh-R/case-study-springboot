package com.cognizant.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRegistrationDetails {
	private String customerId;
	private String password;
	private String firstname;
	private String lastname;
	private String country;
}
