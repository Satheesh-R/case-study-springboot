package com.cognizant.quotesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.quotesservice.model.QuoteDetails;

@Repository
public interface QuotesRepository extends JpaRepository<QuoteDetails, Long> {
	
	@Query("select q from QuoteDetails q where q.username=?1")
	List<QuoteDetails> findAllByUsername(String username);
}