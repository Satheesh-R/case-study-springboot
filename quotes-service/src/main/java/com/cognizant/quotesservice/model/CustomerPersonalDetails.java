package com.cognizant.quotesservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersonalDetails {
	@NotBlank(message = "First name cannnot be empty or null")
	@Size(min = 1,max = 50,message = "Length of firsname have to greater than 1 and lesser than 50")
	private String firstname;
	@NotBlank(message = "Last name cannnot be empty or null")
	@Size(min = 1,max = 50,message = "Length of lastname have to greater than 1 and lesser than 50")
	private String lastname;
	@NotBlank(message = "Gender cannnot be empty or null")
	@Size(min = 1,max = 6,message = "Length of the gender have to greater than 1 and lesser than 6")
	private String gender;
	@Min(value = 1,message="Age cannot be lesser than 1")
	@NotNull(message = "Age cannot be empty or null")
	private int age;
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Enter a valid email id")
	@NotBlank(message = "Emailidcannnot be empty or null")
	private String emailid;
	@Size(min = 10,max = 10,message = "Enter a valid mobile number")
	private Long mobileNumber;
	@Min(value = 1,message="Quote Amount cannot be lesser than 1")
	@NotNull(message = "Quote amount cannot be empty or null")
	private Long quoteAmount;
}