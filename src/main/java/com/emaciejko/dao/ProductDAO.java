package com.emaciejko.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emaciejko.domain.Product;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long> {
    
   public Product findByName(String name);
   
   public Iterable<Product> findByCategory(Product.CategoryEnum category);

}
