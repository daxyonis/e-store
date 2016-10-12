package com.emaciejko.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.CustomerDAO;
import com.emaciejko.domain.Customer;
import com.emaciejko.service.security.EncryptionService;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    
    @Autowired
    public CustomerService(CustomerDAO customerDAO){
	this.customerDAO = customerDAO;
    }
    
    public Iterable<Customer> findAll(){
	return customerDAO.findAll();
    }
    
    public Customer findOne(Integer id){
	return customerDAO.findOne(id);
    }
    
    @Transactional
    public Customer save(Customer customer){
	if(customer.getUser() != null && customer.getUser().getPassword() != null){
	    customer.getUser().setEncryptedPassword(
		    encryptionService.encryptString(customer.getUser().getPassword()));
	}
	return customerDAO.save(customer);
    }
    
    public void delete(Integer id){
	customerDAO.delete(id);
    }
    
}
