package com.emaciejko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emaciejko.domain.Product;
import com.emaciejko.service.ProductService;

@Controller
public class MainController {
    
    private ProductService prodService;
    
    @Autowired
    public MainController(ProductService prodService){
	this.prodService = prodService;
    }

    @RequestMapping("/")
    public String index(){
	return "/view/main";
    }
    
    @RequestMapping("/products")
    public String viewProducts(Model model){
	List<Product> prodList = prodService.findAll();
	model.addAttribute("prodList", prodList);
	return "/view/product/list";
    }
    
    @RequestMapping("/products/{id}")
    public String details(@PathVariable int id, Model model){
	Product prod = prodService.findOne(id);
	model.addAttribute("prod", prod);
	return "/view/product/details";
    }
}
