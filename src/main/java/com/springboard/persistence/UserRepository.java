package com.springboard.persistence;

import org.springframework.data.repository.CrudRepository;

import com.springboard.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

}
