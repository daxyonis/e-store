package com.emaciejko.dao;

import org.springframework.data.repository.CrudRepository;

import com.emaciejko.domain.Order;

public interface OrderDAO extends CrudRepository<Order, Integer>{

}
