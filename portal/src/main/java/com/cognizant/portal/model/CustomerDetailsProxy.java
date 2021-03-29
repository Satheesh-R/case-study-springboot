package com.cognizant.portal.model;


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CustomerDetailsProxy {
	private String firstname;
	private String lastname;
	private String gender;
	private int age;
	private String emailid;
	private Long mobileNumber;
	private Long quoteAmount;
	private MultipartFile fileData;
}