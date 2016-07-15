package com.emaciejko.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	Product prod = new Product("New");
	model.addAttribute("prod", prod);
	model.addAttribute("categoryArray", Product.CategoryEnum.values());
	return "view/product/form";
    }
    
    @RequestMapping(path="/products", method=RequestMethod.POST)    
    public String addProduct(@Valid @ModelAttribute("prod") Product product,
	    //  In the template, we have th:object="${prod}" so for thymeleaf errors to print
	    //  we have to associate the same object name, here we do it using @ModelAttribute("prod").
	    // see http://stackoverflow.com/questions/22948257/thymeleaf-not-displaying-spring-form-error-messages?rq=1
	    BindingResult bindingRes,
	    Model model,
	    RedirectAttributes redirectAttr){
	
	if(bindingRes.hasErrors()){
	    model.addAttribute("prod", product);
	    model.addAttribute("categoryArray", Product.CategoryEnum.values());
	    return "view/product/form";
	}
	
	product = prodService.save(product);
	// flash the success message	    
	redirectAttr.addAttribute("id", product.getId());
	redirectAttr.addFlashAttribute("success", "Product  (" + product.getName() + ") was successfully saved.");
	return "redirect:/products/{id}";
    }
}
