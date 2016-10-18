package com.emaciejko.dao;

import org.springframework.data.repository.CrudRepository;

import com.emaciejko.domain.Role;

public interface RoleDAO extends CrudRepository<Role, Integer> {

}
