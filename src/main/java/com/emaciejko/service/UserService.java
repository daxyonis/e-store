package com.emaciejko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.UserDAO;
import com.emaciejko.domain.User;

@Service
public class UserService {
        
    private UserDAO userDAO;
    
    @Autowired
    public void setUserDAO(UserDAO userDAO){
	this.userDAO = userDAO;
    }
    
    public User find(Integer id){
	return userDAO.findOne(id);
    }
    
    public List<User> findAll(){
	return (List<User>) userDAO.findAll();
    }
   
    public User save(User user){
	User newUser = userDAO.save(user);
	return newUser;
    }
    
    public void delete(Integer id){
	userDAO.delete(id);
    }
}
