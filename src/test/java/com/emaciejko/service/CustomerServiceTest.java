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
import com.emaciejko.domain.User;

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
	Customer customer = customerService.findOne(1);
	assertTrue(customer != null);
	assertTrue(customer.getId() == 1L);
    }
    
    private Customer createCustomer(){
	return new Customer("Jane", "Doe", "janedoe@mail.com", "555-666-7777", "Vancouver", "BC");
    }
    
    private void checkCustomer(Customer expected, Customer other){
	assertEquals(expected.getFirstName(), other.getFirstName());
	assertEquals(expected.getLastName(), other.getLastName());
	assertEquals(expected.getEmail(), other.getEmail());
	assertEquals(expected.getPhoneNb(), other.getPhoneNb());
	assertEquals(expected.getCity(), other.getCity());
	assertEquals(expected.getProvince(), other.getProvince());
    }
    
    @Test
    public void saveTest(){
	Customer newCustomer = createCustomer();
	Customer saved = customerService.save(newCustomer);
	assertTrue(saved != null);
	assertTrue(saved.getId() > 0);
	checkCustomer(newCustomer, saved);
    }   
    
    @Test
    public void saveWithUserTest(){
	Customer newCustomer = createCustomer();
	User user = new User();
	user.setUsername("eva");
	user.setPassword("mypassword");
	newCustomer.setUser(user);
	Customer saved = customerService.save(newCustomer);
	assertTrue(saved != null);
	assertTrue(saved.getId() > 0);
	checkCustomer(newCustomer, saved);
	assertTrue(saved.getUser().getId() != null);
    }
    
    @Test
    public void deleteTest(){
	customerService.delete(1);
	List<Customer> customers = (List<Customer>) customerService.findAll();
	assertEquals(2, customers.size());
    }
    
}
