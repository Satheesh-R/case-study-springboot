package com.cognizant.quotesservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quotes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long quoteAmount;
}