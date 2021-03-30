package com.cognizant.quotesservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuotesMaster {
	@Id
	private int quoteId;
	private Long minAge;
	private Long maxAge;
	private Long quoteAmount;
}