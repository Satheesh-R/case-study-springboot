package com.cognizant.quotesservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	private Long mobileNumber;
	private Long quoteAmount;
	@Lob
	private Byte[] fileData;
	public QuoteDetails(String username, String firstname, String lastname, String gender, int age, String emailid,
			Long mobileNumber, Long quoteAmount,Byte[] data) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.emailid = emailid;
		this.mobileNumber = mobileNumber;
		this.quoteAmount = quoteAmount;
		this.fileData = data;
	}
}