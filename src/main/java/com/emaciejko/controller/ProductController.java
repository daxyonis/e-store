package com.emaciejko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emaciejko.domain.Product;
import com.emaciejko.service.ProductService;

@Controller
public class ProductController {
    
    private ProductService prodService;
    
    @Autowired
    public ProductController(ProductService prodService){
	this.prodService = prodService;
    }

    @RequestMapping(path="/products", method=RequestMethod.GET)
    public String viewProducts(Model model){
	Iterable<Product> prodList = prodService.findAll();
	model.addAttribute("prodList", prodList);
	return "/view/product/list";
    }
    
    @RequestMapping(path="/products/{id}", method=RequestMethod.GET)
    public String details(@PathVariable int id, Model model){
	Product prod = prodService.findOne(id);
	model.addAttribute("prod", prod);
	return "/view/product/details";
    }        
    
    @RequestMapping(path="/products/new", method=RequestMethod.GET)
    public String newProduct(Model model){
	Product prod = new Product("",Product.CategoryEnum.TEA, 0.00, 0);
	model.addAttribute("prod", prod);
	model.addAttribute("categoryArray", Product.CategoryEnum.values());
	return "view/product/form";
    }
}
