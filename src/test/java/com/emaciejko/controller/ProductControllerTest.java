package com.emaciejko.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.emaciejko.domain.Product;
import com.emaciejko.service.ProductService;

public class ProductControllerTest {

    @Mock
    private ProductService prodService;
    
    @InjectMocks
    private ProductController prodController;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(prodController).build();
    }
    
    @Test
    public void listTest() throws Exception{
	List<Product> prods = new ArrayList<>();
	prods.add(new Product("Prod 1"));
	prods.add(new Product("Prod 2"));
	
	when(prodService.findAll()).thenReturn(prods);
	
	mockMvc.perform(get("/products"))
		.andExpect(status().isOk())
		.andExpect(view().name("view/product/list"))
		.andExpect(model().attribute("prodList", hasSize(2)));
	
    }
    
    @Test
    public void detailsTest() throws Exception{
	Long id = (long)1;
	
	when(prodService.findOne(id)).thenReturn(new Product("Prod " + id));
	
	mockMvc.perform(get("/product/" + id))
		.andExpect(status().isOk())
		.andExpect(view().name("view/product/details"))
		.andExpect(model().attribute("prod", instanceOf(Product.class)))
		.andExpect(model().attribute("prod", hasProperty("name", is("Prod 1"))));
    }
    
    @Test
    public void newTest() throws Exception {
	
	mockMvc.perform(get("/product/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("view/product/form"))
		.andExpect(model().attribute("prod", instanceOf(Product.class)))
		.andExpect(model().attribute("prod", hasProperty("name", is("New"))))
		.andExpect(model().attribute("categoryArray", arrayContaining(Product.CategoryEnum.values())));
    }
    
    
    @Test
    public void editTest() throws Exception {
	Long id = (long)1;
	
	when(prodService.findOne(id)).thenReturn(new Product("Prod " + id));
	
	mockMvc.perform(get("/product/edit/" + id))
		.andExpect(status().isOk())
		.andExpect(view().name("view/product/form"))
		.andExpect(model().attribute("prod", instanceOf(Product.class)))
		.andExpect(model().attribute("prod", hasProperty("name", is("Prod 1"))))
		.andExpect(model().attribute("categoryArray", arrayContaining(Product.CategoryEnum.values())));
	
    }
    
    @Test
    public void addProductTest() throws Exception{
	
	Long id = 1L;
	String prodName = "Super Tea";
	Product.CategoryEnum prodCategory = Product.CategoryEnum.WHITE_TEA;
	BigDecimal prodPrice = BigDecimal.valueOf(14.50);
	int prodNbStock = 10;
	
	Product prod = new Product(prodName, prodCategory, prodPrice, prodNbStock);
	
	when(prodService.save(Matchers.<Product>any())).thenReturn(prod);
	
	mockMvc.perform(post("/products")
		.param("id", "1")
		.param("name",prodName)
		.param("category", prodCategory.toString())
		.param("price", prodPrice.toPlainString())
		.param("nbInStock", String.valueOf(prodNbStock)))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:admin/inventory"));
		
	
	//verify properties of bound object
        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(prodService).save(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(prodName, boundProduct.getValue().getName());
        assertEquals(prodCategory, boundProduct.getValue().getCategory());
        assertEquals(prodPrice, boundProduct.getValue().getPrice());
        assertEquals(prodNbStock, boundProduct.getValue().getNbInStock());
	
    }
    
    
    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;

        mockMvc.perform(delete("/product/" + id)
        	.param("id", String.valueOf(id)))
                .andExpect(status().isOk());
                

        verify(prodService, times(1)).delete(id);
    }
}
