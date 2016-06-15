package com.emaciejko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emaciejko.service.ProductService;

@Controller
public class AdminController {
    
    private ProductService prodService;
    
    @Autowired
    public AdminController(ProductService prodService){
	this.prodService = prodService;
    }
    
    @RequestMapping("/admin")
    public String adminPage(){
	return "/view/admin/admin";
    }
    
    @RequestMapping("/admin/inventory")
    public String adminInventoryPage(Model model){
	model.addAttribute("prodList", prodService.findAll());
	return "/view/admin/inventory";
    }
}
