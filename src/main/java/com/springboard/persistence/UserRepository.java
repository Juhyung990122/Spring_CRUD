package com.springboard.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboard.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query("SELECT u.uid, b.title FROM User u LEFT OUTER JOIN Board b ON u.uid = b.user")
	List<Object[]> getUserWithBoardTitle(String string);

}
