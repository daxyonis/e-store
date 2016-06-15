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
    
    private String label;
    private String category;
    private String description = "";
    private BigDecimal price;    
    private boolean active = true;
    private LocalDate dateSaleOpen;
    private String city;
    private String state;

    private Product(){}
    
    public Product(String label, String category, double price, String city, String state){
	this.label = label;
	this.category = category;
	this.price = BigDecimal.valueOf(price);
	this.city = city;
	this.state = state;
	this.dateSaleOpen = LocalDate.now();
    }

    public Long getId(){
	return this.id;
    }
    public void setId(Long id){
	this.id = id;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public LocalDate getDateSaleOpen() {
        return dateSaleOpen;
    }
    
    public Date getDateSaleOpenAsDate(){
	return Date.valueOf(dateSaleOpen);
    }

    public void setDateSaleOpen(LocalDate dateSaleOpen) {
        this.dateSaleOpen = dateSaleOpen;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    

  
    
}
