package com.emaciejko.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
    
    public enum CategoryEnum {GREEN_TEA, BLACK_TEA, WHITE_TEA, TEA}; 
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;    
    private CategoryEnum category;
    private String description;
    private BigDecimal price;    
    private boolean active = true;    
    private int nbInStock = 0;
    private String imgFilename = "";
    
    private Product(){}
    
    public Product(String name, CategoryEnum category, double price, int inStock){
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

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
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

    @Override
    public String toString() {
	return "Product [id=" + id + ", name=" + name + ", category=" + category.toString() + ", description=" + description
		+ ", price=" + price + ", active=" + active + ", nbInStock=" + nbInStock + ", imgFilename="
		+ imgFilename + "]";
    }

   
   
    
}
