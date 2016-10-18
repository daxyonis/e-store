package com.emaciejko.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.UserDAO;
import com.emaciejko.domain.User;
import com.emaciejko.service.security.EncryptionService;

@Service
public class UserService {
        
    private UserDAO userDAO;
    private EncryptionService encryptionService;
    
    @Autowired
    public void setUserDAO(UserDAO userDAO){
	this.userDAO = userDAO;
    }
    
    @Autowired
    public void setEncryptionService(EncryptionService es){
	this.encryptionService = es;
    }
    
    public User find(Integer id){
	return userDAO.findOne(id);
    }
    
    public List<User> findAll(){
	return (List<User>) userDAO.findAll();
    }
   
    @Transactional
    public User save(User user){
	if(user.getPassword() != null){
	    user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
        }
	
	User newUser = userDAO.save(user);
	return newUser;
    }
    
    @Transactional
    public void delete(Integer id){
	userDAO.delete(id);
    }
}
