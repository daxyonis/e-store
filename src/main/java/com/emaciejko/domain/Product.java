package com.emaciejko.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//import lombok.Data;

//@Entity
public class Product {
    
//    @Id @GeneratedValue
    private Long id;
    
    private String name;
    private String category;
    private String description = "";
    private BigDecimal price;    
    private boolean active = true;    
    private int nbInStock = 0;
    private String imgFilename = "";
    
    private Product(){}
    
    public Product(String name, String category, double price, int inStock){
	this.name = name;
	this.category = category;
	this.price = BigDecimal.valueOf(price);
	this.nbInStock = inStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getNbInStock() {
        return nbInStock;
    }

    public void setNbInStock(int nbInStock) {
        this.nbInStock = nbInStock;
    }

    public String getImgFilename() {
        return imgFilename;
    }

    public void setImgFilename(String imgFilename) {
        this.imgFilename = imgFilename;
    }
    

   
    
}
