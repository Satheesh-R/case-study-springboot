package com.cognizant.customerauthenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.customerauthenticationservice.model.CustomerDetails;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails,String>{

}