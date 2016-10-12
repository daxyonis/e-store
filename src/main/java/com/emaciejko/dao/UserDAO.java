package com.emaciejko.dao;

import org.springframework.data.repository.CrudRepository;

import com.emaciejko.domain.User;

public interface UserDAO extends CrudRepository<User,Integer>{

}
