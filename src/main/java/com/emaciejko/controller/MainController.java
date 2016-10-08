package com.emaciejko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emaciejko.util.PrintBeans;

@Controller
public class MainController { 
    
    @Autowired
    private PrintBeans pb;

    @RequestMapping("/")
    @ResponseBody
    public List<String> index(){
	
	return pb.getBeanNames();
    }
    
    @RequestMapping("/loc")
    @ResponseBody
    public String loc(){
	
	return pb.checkLocaleResolver();
    }
    
   
}
