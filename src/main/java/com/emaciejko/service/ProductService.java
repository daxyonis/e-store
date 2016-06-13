package com.emaciejko.service;

import java.util.List;

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
    
    public List<Product> findAll(){
	return productDAO.findAll();
    }
}
