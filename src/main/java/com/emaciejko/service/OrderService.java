package com.emaciejko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaciejko.dao.OrderDAO;
import com.emaciejko.domain.Order;

@Service
public class OrderService  {
    
    private OrderDAO orderDAO;
    
    @Autowired
    public void setOrderDAO(OrderDAO orderDAO){
	this.orderDAO = orderDAO;
    }
    
    public List<Order> findAll(){
	return (List<Order>) orderDAO.findAll();
    }
    
    public Order findOne(Integer id){
	return orderDAO.findOne(id);
    }
    
    public Order save(Order order){
	return orderDAO.save(order);
    }
    
    public void delete(Integer id){
	orderDAO.delete(id);
    }
    
}