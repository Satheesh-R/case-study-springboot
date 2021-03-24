package com.cognizant.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersonalDetails {
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	private String emailid;
	private Long mobileNumber;
	private Long quoteAmount;
}