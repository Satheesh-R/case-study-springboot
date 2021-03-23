package com.cognizant.quotesservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CustomerDetails {
	@Min(value = 1,message="Age cannot be lesser than 1")
	@NotNull(message = "Age cannot be empty or null")
	private Long age;
	@NotBlank(message = "Drinker cannot be empty or null,It have to either YES or NO")
	@Size(min = 3,max = 3)
	private String drinker;
	@NotBlank(message = "Smoker cannot be empty or null,It have to either YES or NO")
	@Size(min = 3,max = 3)
	private String smoker;
}