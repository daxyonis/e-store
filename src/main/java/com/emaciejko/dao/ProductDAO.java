package com.emaciejko.dao;

import java.util.List;

import com.emaciejko.domain.Product;

public interface ProductDAO {
    
    public List<Product> findAll();
    
    public Product findById(int id);

}
