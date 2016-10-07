package com.emaciejko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emaciejko.domain.Customer;
import com.emaciejko.domain.Province;
import com.emaciejko.service.CustomerService;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;
    
    @Autowired
    public void setCustomerService(CustomerService customerService){
	this.customerService = customerService;
    }
    
   @RequestMapping({"/list","/"})
   public String list(Model model){
       model.addAttribute("customerList", customerService.findAll());
       return "view/customer/list";
   }
   
   @RequestMapping("/show/{id}")
   public String show(@PathVariable Long id, Model model){
       model.addAttribute("customer", customerService.findOne(id));
       return "view/customer/show";
   }
   
   @RequestMapping("/edit/{id}")
   public String edit(@PathVariable Long id, Model model){
       model.addAttribute("customer", customerService.findOne(id));
       model.addAttribute("provinces", Province.getKeyedNames());
       return "view/customer/customerForm";
   }
      
   @RequestMapping("/new")
   public String newOne(Model model){
       model.addAttribute("customer", new Customer("new"));
       model.addAttribute("provinces", Province.getKeyedNames());
       return "view/customer/customerForm";
   }
   
   @RequestMapping(method=RequestMethod.POST)
   public String save(Customer customer){
       Customer savedCustomer = customerService.save(customer);
       return "redirect:/customer/show/" + savedCustomer.getId();
   }
   
   @RequestMapping("/delete/{id}")
   public String delete(@PathVariable Long id){
       customerService.delete(id);
       return "redirect:/customer/list";
   }
}
