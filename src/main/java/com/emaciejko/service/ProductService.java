package com.emaciejko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.ProductDAO;
import com.emaciejko.domain.Product;

@Service
public class ProductService {

    private ProductDAO productDAO;
    
    @Autowired
    public ProductService(ProductDAO productDAO){
	this.productDAO = productDAO;
    }
    
    public Iterable<Product> findAll(){
	return productDAO.findAll();
    }
    
    public Product findOne(Integer id){
	return productDAO.findOne(id);
    }
    
    public Product save(Product product){
	return productDAO.save(product);
    }
    
    public void delete(Integer id){
	productDAO.delete(id);
    }
}
