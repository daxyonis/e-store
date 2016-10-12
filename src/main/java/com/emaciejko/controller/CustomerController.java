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
    
    /**
     * GET method
     * Show a list of all customers
     * @param    model
     * @return   the view of all customers
     */
   @RequestMapping({"/list","/"})
   public String list(Model model){
       model.addAttribute("customerList", customerService.findAll());
       return "view/customer/list";
   }
   
   /**
    * GET method
    * Show one customer
    * @param id     the id of customer to view
    * @param model
    * @return       the view for one given customer
    */
   @RequestMapping("/show/{id}")
   public String show(@PathVariable Long id, Model model){
       model.addAttribute("customer", customerService.findOne(id));
       return "view/customer/show";
   }
   
   /**
    * GET method
    * Shows form to edit one customer
    * @param id    the id of customer to edit
    * @param model
    * @return      the view to edit the given customer
    */
   @RequestMapping("/edit/{id}")
   public String edit(@PathVariable Long id, Model model){
       model.addAttribute("customer", customerService.findOne(id));
       model.addAttribute("provinces", Province.getKeyedNames());
       return "view/customer/customerForm";
   }
      
   /**
    * GET method
    * Shows form to create new customer
    * @param model
    * @return      the view to create a new customer
    */
   @RequestMapping("/new")
   public String newOne(Model model){
       model.addAttribute("customer", new Customer("new"));
       model.addAttribute("provinces", Province.getKeyedNames());
       return "view/customer/customerForm";
   }
   
   /**
    * POST method
    * Saves a new or updated customer
    * @param customer   the customer object to save
    * @return           redirects to view the saved customer
    */
   @RequestMapping(method=RequestMethod.POST)
   public String save(Customer customer){
       Customer savedCustomer = customerService.save(customer);
       return "redirect:/customer/show/" + savedCustomer.getId();
   }
   
   /**
    * GET method
    * Deletes a customer
    * @param id      the id of customer to delete
    * @return        redirects to list of customers
    */
   @RequestMapping("/delete/{id}")
   public String delete(@PathVariable Long id){
       customerService.delete(id);
       return "redirect:/customer/list";
   }
}
