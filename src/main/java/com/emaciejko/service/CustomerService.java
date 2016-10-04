package com.emaciejko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.CustomerDAO;
import com.emaciejko.domain.Customer;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    
    @Autowired
    public CustomerService(CustomerDAO customerDAO){
	this.customerDAO = customerDAO;
    }
    
    public Iterable<Customer> findAll(){
	return customerDAO.findAll();
    }
    
    public Customer findOne(Long id){
	return customerDAO.findOne(id);
    }
    
    public Customer save(Customer product){
	return customerDAO.save(product);
    }
    
    public void delete(Long id){
	customerDAO.delete(id);
    }
    
}
