package com.emaciejko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emaciejko.domain.Province;
import com.emaciejko.domain.User;
import com.emaciejko.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService){
	this.userService = userService;
    }
    
    @RequestMapping({"/","/list"})
    public String list(Model model){
	model.addAttribute("users", userService.findAll());
	return "view/user/list";
    }
    
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model){
	model.addAttribute("user", userService.find(id));
	return "view/user/show";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
	model.addAttribute("user", userService.find(id));
	model.addAttribute("provinces", Province.getKeyedNames());
	return "view/user/userForm";
    }
    
    @RequestMapping("/new")
    public String edit(Model model){
	model.addAttribute("user", new User());
	model.addAttribute("provinces", Province.getKeyedNames());
	return "view/user/userForm";
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String save(User user){
	User savedUser = userService.save(user);
	return "redirect:/user/show/" + savedUser.getId();
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
	userService.delete(id);
	return "redirect:/user/list";
    }
}
