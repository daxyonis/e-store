package com.emaciejko.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Entity
public class Product implements DomainObject {
    
    public enum CategoryEnum {GREEN_TEA, BLACK_TEA, WHITE_TEA, TEA}; 
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Version
    private Integer version;
    
    @NotEmpty(message="The product name must not be empty.")
    private String name;
    @NotNull(message="You must choose a category.")
    private CategoryEnum category;
    private String description; 
    @NotNull(message="You must enter a price.")
    @Min(value=0, message="The price must be greater than zero.")
    private BigDecimal price;    
    private boolean active = true;
    @Min(value=0, message="The number in stock must be greater than or equal to zero.")
    private int nbInStock = 0;
    
    @Transient
    private MultipartFile image;    
    
    private Product(){}
    
    public Product(String name){
	this.name = name;	
    }
    
    public Product(String name, CategoryEnum category, BigDecimal price, int inStock){
	this.name = name;
	this.category = category;
	this.price = price;
	this.nbInStock = inStock;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
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
      
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
	return "Product [id=" + id + ", name=" + name + ", category=" + category.toString() + ", description=" + description
		+ ", price=" + price + ", active=" + active + ", nbInStock=" + nbInStock + "]";
    }

   
   
    
}
