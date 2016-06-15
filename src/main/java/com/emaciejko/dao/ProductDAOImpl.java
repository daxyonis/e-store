package com.emaciejko.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emaciejko.domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private List<Product> productList = new ArrayList<Product>();

    @Override
    public List<Product> findAll() {
	Product product = new Product("C-2857809348",
				      "Condo",
				      1600000.00,
				      "San Jose",
				      "California");
	
	product.setDescription("Magnificient condo sea overview");
	product.setId((long) productList.size());
	productList.add(product);	
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
