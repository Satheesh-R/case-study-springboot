package com.cognizant.quotesservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class QuoteDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quoteId;
	private String username;
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	private String emailid;
	private String mobileNumber;
	private Long quoteAmount;
	public QuoteDetails(String username, String firstname, String lastname, String gender, int age, String emailid,
			String mobileNumber, Long quoteAmount) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.emailid = emailid;
		this.mobileNumber = mobileNumber;
		this.quoteAmount = quoteAmount;
	}
}