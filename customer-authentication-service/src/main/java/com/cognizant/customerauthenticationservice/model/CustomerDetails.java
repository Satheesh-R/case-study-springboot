package com.cognizant.customerauthenticationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails
{
	@Id
	@Column(unique = true)
	private String customerId;
	private String password;
	private String firstname;
	private String lastname;
	private String country;
}