package com.emaciejko.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emaciejko.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {
    
    
    private ProductService prodService;

    @Autowired
    public void setProductService(ProductService prodService){
	this.prodService = prodService;
    }
    
    @Test
    public void findAllTest(){
	List<Product> products = (List<Product>) prodService.findAll();
	
	assertEquals(4, products.size());
    }
    
    @Test
    public void findOneTest(){
	Product prod = prodService.findOne(1);
	assertTrue(1L == prod.getId());
    }
    
    @Test
    public void saveTest(){
	Product prod = new Product("White Jasmin", Product.CategoryEnum.WHITE_TEA, BigDecimal.valueOf(25.00), 8);
	
	Product savedProd = prodService.save(prod);
	
	assertTrue(savedProd != null);
	assertTrue(savedProd.getId() > 4L);
	assertEquals(prod.getName(), savedProd.getName());
	assertEquals(prod.getCategory(), savedProd.getCategory());
	assertEquals(prod.getPrice(), savedProd.getPrice());
	assertEquals(prod.getNbInStock(), savedProd.getNbInStock());
		
    }
    
    @Test
    public void deleteTest(){
	
	prodService.delete(1);
	List<Product> products = (List<Product>) prodService.findAll();	
	assertEquals(3, products.size());
    }
}
