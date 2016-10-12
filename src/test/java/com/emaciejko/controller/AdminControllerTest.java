package com.emaciejko.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emaciejko.domain.Product;
import com.emaciejko.service.ProductService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for AdminController 
 * @author Eva
 *
 */
public class AdminControllerTest {

    @Mock
    private ProductService prodService;
    
    @InjectMocks
    private AdminController adminController;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    
    @Test
    public void adminPageTest() throws Exception{
	mockMvc.perform(get("/admin"))
		.andExpect(status().isOk())
		.andExpect(view().name("view/admin/admin"));
	
    }
    
    @Test
    public void adminInventoryPageTest() throws Exception{
	List<Product> prods = new ArrayList<>();
	prods.add(new Product("Prod 1"));
	prods.add(new Product("Prod 2"));
	
	when(prodService.findAll()).thenReturn(prods);
	
	mockMvc.perform(get("/admin/inventory"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("view/admin/inventory"))
        	.andExpect(model().attribute("prodList", hasSize(2)));
    }
}
