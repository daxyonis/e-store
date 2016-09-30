package com.emaciejko;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emaciejko.dao.ProductDAO;
import com.emaciejko.domain.Product;
import com.emaciejko.domain.Product.CategoryEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
public class EStoreApplication {  
    
    private static final Logger log = LoggerFactory.getLogger(EStoreApplication.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    public static void main(String[] args) {
	SpringApplication.run(EStoreApplication.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(ProductDAO repository) {
		return (args) -> {
			// save a couple of customers
		    	List<Product> addedProducts = new ArrayList<>();
		    	addedProducts.add(repository.save(new Product("Black Dragon", CategoryEnum.BLACK_TEA, 16.0, 25)));
		    	addedProducts.add(repository.save(new Product("Dark Lord", CategoryEnum.BLACK_TEA, 16.0, 25)));
		    	addedProducts.add(repository.save(new Product("White Dragon", CategoryEnum.WHITE_TEA, 17.0, 22)));
		    	addedProducts.add(repository.save(new Product("Matcha", CategoryEnum.GREEN_TEA, 20.0, 20)));

			// fetch all customers
			log.info("Products added with save():");
			log.info("-------------------------------");
			for (Product prod : addedProducts) {
			    log.info(prod.toString());
			}
			log.info("");


			// fetch all customers
			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product prod : repository.findAll()) {
				log.info(prod.toString());
			}
        log.info("");

			// fetch an individual customer by ID
        		Product prod = repository.findOne(1L);
			log.info("Product found with findOne(1L):");
			log.info("--------------------------------");
			log.info(prod.toString());
        log.info("");

			// fetch customers by last name
			log.info("Product found with findByCategory('Black Tea'):");
			log.info("--------------------------------------------");
			for (Product black : repository.findByCategory(CategoryEnum.BLACK_TEA)) {
				log.info(black.toString());
			}
        log.info("");
		};
	}
}
