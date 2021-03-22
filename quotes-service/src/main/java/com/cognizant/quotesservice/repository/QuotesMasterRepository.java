package com.cognizant.quotesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.quotesservice.model.QuotesMaster;

@Repository
public interface QuotesMasterRepository extends JpaRepository<QuotesMaster, Integer>{
	
	@Query("select q.quoteAmount from QuotesMaster q where (q.minAge<=:age and q.maxAge>=:age)")
	Long getQuoteAmount(@Param("age")Long age);
}