package com.emaciejko.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emaciejko.domain.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long> {  
   
   
   

}
