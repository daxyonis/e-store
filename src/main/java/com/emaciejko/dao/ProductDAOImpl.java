package com.emaciejko.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emaciejko.domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private List<Product> productList = new ArrayList<Product>();
    
    public ProductDAOImpl(){
	Product product = new Product("Matcha","Green Tea", 15.00, 20);
	
	product.setDescription("Subtly flavoured, the true essence of Matcha is captured by this tea.");
	product.setId((long) productList.size());
	product.setImgFilename("matcha.jpg");
	productList.add(product);
	
	product = new Product("Sencha","Green Tea", 10.00, 20);
	
	product.setDescription("Authentic Japanese Sencha with a sweet aroma.");
	product.setId((long) productList.size());
	product.setImgFilename("sencha.jpg");
	productList.add(product);
    }

    @Override
    public List<Product> findAll() {		
	return productList;
    }

    @Override
    public Product findById(int id) {
	if(id >= 0 && id < productList.size()){
	    return productList.get(id);
	}
	else{
	    return null;
	}
    }
    
    
}
