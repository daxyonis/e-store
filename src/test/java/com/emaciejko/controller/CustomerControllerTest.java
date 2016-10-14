package com.emaciejko.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emaciejko.domain.Customer;
import com.emaciejko.domain.Province;
import com.emaciejko.service.CustomerService;

/**
 * Unit test for CustomerController
 * @author Eva
 *
 */
public class CustomerControllerTest {
    
    @Mock
    private CustomerService customerService;
    
    @InjectMocks
    private CustomerController customerController;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    

    @Test 
    public void listTest() throws Exception{
	List<Customer> customers = new ArrayList<>();
	customers.add(new Customer("Charles"));
	customers.add(new Customer("Eloïse"));
	
	when(customerService.findAll()).thenReturn(customers);
	
	mockMvc.perform(get("/customer/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("view/customer/list"))
		.andExpect(model().attribute("customerList", hasSize(2)));
	
    }
    
    @Test
    public void showTest() throws Exception{
	Customer customer = new Customer("Joe");
	Integer id = 1;
	customer.setId(id);
	
	when(customerService.findOne(id)).thenReturn(customer);
	
	mockMvc.perform(get("/customer/show/" + id))
		.andExpect(status().isOk())
		.andExpect(view().name("view/customer/show"))
		.andExpect(model().attribute("customer", is(customer)));
	
    }
    
    @Test
    public void editTest() throws Exception{
	Customer customer = new Customer("Joe");
	Integer id = 1;
	customer.setId(id);
	
	when(customerService.findOne(id)).thenReturn(customer);
	
	Map<Province,String> map = Province.getKeyedNames();
	
	mockMvc.perform(get("/customer/edit/" + id))
	.andExpect(status().isOk())
	.andExpect(view().name("view/customer/customerForm"))
	.andExpect(model().attribute("customer", is(customer)))
	.andExpect(model().attribute("provinces", is(map)));
	
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void newTest() throws Exception{
	Map<Province,String> map = Province.getKeyedNames();
	
	mockMvc.perform(get("/customer/new"))
	.andExpect(status().isOk())
	.andExpect(view().name("view/customer/customerForm"))
	.andExpect(model().attribute("customer", 
		allOf(
			hasProperty("firstName",is("new")))))
	.andExpect(model().attribute("provinces", is(map)));		
    }
    
    
    @Test
    public void saveTest() throws Exception{
	Customer customer = new Customer("John","Doe","johndoe@mail.com","819-384-5872", "Quebec", "QC");
	customer.setId(5);
	
	when(customerService.save(Matchers.<Customer>any())).thenReturn(customer);
	
	mockMvc.perform(post("/customer")
        		.param("firstName", customer.getFirstName())
        		.param("lastName", customer.getLastName())
        		.param("email", customer.getEmail())
        		.param("phoneNb", customer.getPhoneNb())
        		.param("city", customer.getBillingAddress().getCity())
        		.param("province", customer.getBillingAddress().getProvince().toString()))		
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/customer/show/" + customer.getId()));	
	
	//verify properties of bound object
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).save(boundCustomer.capture());
        
        assertEquals(customer.getFirstName(), boundCustomer.getValue().getFirstName());
        assertEquals(customer.getLastName(), boundCustomer.getValue().getLastName());
        assertEquals(customer.getEmail(), boundCustomer.getValue().getEmail());
        assertEquals(customer.getPhoneNb(), boundCustomer.getValue().getPhoneNb());
        assertEquals(customer.getBillingAddress().getCity(), boundCustomer.getValue().getBillingAddress().getCity());
        assertEquals(customer.getBillingAddress().getProvince(), boundCustomer.getValue().getBillingAddress().getProvince());
                
    }
    
    @Test
    public void deleteTest() throws Exception{
	Integer id = 1;
	
	 mockMvc.perform(get("/customer/delete/" + id))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(view().name("redirect:/customer/list"));
	                

	        verify(customerService, times(1)).delete(id);
    }
}
