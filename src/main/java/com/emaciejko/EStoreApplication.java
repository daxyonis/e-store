package com.emaciejko;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emaciejko.dao.CustomerDAO;
import com.emaciejko.dao.ProductDAO;
import com.emaciejko.domain.Product;
import com.emaciejko.domain.Product.CategoryEnum;
import com.emaciejko.domain.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
public class EStoreApplication {  
    
    private static final Logger log = LoggerFactory.getLogger(EStoreApplication.class);

    public static void main(String[] args) {
	SpringApplication.run(EStoreApplication.class, args);
    }    
    
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    public CommandLineRunner demo1(ProductDAO prodRepo) {
		return (args) -> {					    
		    	// save a couple of products
		    	List<Product> addedProducts = new ArrayList<>();
		    	prodRepo.deleteAll();
		    	addedProducts.add(prodRepo.save(new Product("Black Dragon", CategoryEnum.BLACK_TEA, 16.0, 25)));
		    	addedProducts.add(prodRepo.save(new Product("Dark Lord", CategoryEnum.BLACK_TEA, 16.0, 25)));
		    	addedProducts.add(prodRepo.save(new Product("White Dragon", CategoryEnum.WHITE_TEA, 17.0, 22)));
		    	addedProducts.add(prodRepo.save(new Product("Matcha", CategoryEnum.GREEN_TEA, 20.0, 20)));			
        
//			log.info("Products added with save():");
//			log.info("-------------------------------");
//			for (Product prod : addedProducts) {
//			    log.info(prod.toString());
//			}
//			log.info("");


			// fetch all products
			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product prod : prodRepo.findAll()) {
				log.info(prod.toString());
			}
			log.info("");
//
//			// fetch an individual customer by ID
//        		Product prod = prodRepo.findOne(1L);
//			log.info("Product found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(prod.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Product found with findByCategory('Black Tea'):");
//			log.info("--------------------------------------------");
//			for (Product black : prodRepo.findByCategory(CategoryEnum.BLACK_TEA)) {
//				log.info(black.toString());
//			}
//			log.info("");
		};
	}
    
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 2)
    public CommandLineRunner demo2(CustomerDAO custRepo){
	return (args) -> {
	    	// save a couple of customers
	    	List<Customer> addedCustomers = new ArrayList<>();
	    	addedCustomers.add(custRepo.save(new Customer("Bobby", "Bazini", "bobbizzi@starz.net", "814-842-4224", "Quebec", "QC")));
	    	addedCustomers.add(custRepo.save(new Customer("Lydia", "Sarcelle", "lidy1234@gmail.com", "(617)444-1234", "Montréal", "QC")));
	    	addedCustomers.add(custRepo.save(new Customer("Norman", "Weisle", "nweisle@canadagvt.ca", "(819)303-4004 #554", "Ottawa", "ON")));
	 
	    	// fetch all customers
	    	log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer cust : custRepo.findAll()) {
			log.info(cust.toString());
		}
		log.info("");
	};
    }
}
