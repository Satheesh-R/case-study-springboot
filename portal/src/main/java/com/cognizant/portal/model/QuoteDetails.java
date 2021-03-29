package com.cognizant.portal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuoteDetails {
	private Long quoteId;
	private String username;
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	private String emailid;
	private Long mobileNumber;
	private Long quoteAmount;
}