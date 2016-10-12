package com.emaciejko.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emaciejko.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional   
public class CustomerServiceTest {

    private CustomerService customerService;
    
    @Autowired
    public void setCustomerService(CustomerService customerService){
	this.customerService = customerService;
    }
    
    @Test
    public void findAllTest(){
	List<Customer> customers = (List<Customer>) customerService.findAll();
	assertEquals(3, customers.size());
    }
    
    @Test
    public void findOneTest(){
	Customer customer = customerService.findOne(1L);
	assertTrue(customer != null);
	assertTrue(customer.getId() == 1L);
    }
    
    @Test
    public void saveTest(){
	Customer newCustomer = new Customer("Jane", "Doe", "janedoe@mail.com", "555-666-7777", "Vancouver", "BC");
	Customer saved = customerService.save(newCustomer);
	assertTrue(saved != null);
	assertTrue(saved.getId() > 0);
	assertEquals(newCustomer.getFirstName(), saved.getFirstName());
	assertEquals(newCustomer.getLastName(), saved.getLastName());
	assertEquals(newCustomer.getEmail(), saved.getEmail());
	assertEquals(newCustomer.getPhoneNb(), saved.getPhoneNb());
	assertEquals(newCustomer.getCity(), saved.getCity());
	assertEquals(newCustomer.getProvince(), saved.getProvince());
    }
    
    @Test
    public void deleteTest(){
	customerService.delete(1L);
	List<Customer> customers = (List<Customer>) customerService.findAll();
	assertEquals(2, customers.size());
    }
    
}
