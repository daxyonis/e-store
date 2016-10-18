package com.emaciejko.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.RoleDAO;
import com.emaciejko.domain.Role;

@Service
public class RoleService {
    
    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO){
	this.roleDAO = roleDAO;
    }
    
    public List<Role> findAll(){
	return (List<Role>) roleDAO.findAll();
    }
    
    public Role findOne(Integer id){
	return roleDAO.findOne(id);
    }
    
    @Transactional
    public Role save(Role role){
	return roleDAO.save(role);
    }
    
    @Transactional
    public void delete(Integer id){
	roleDAO.delete(id);
    }
}
