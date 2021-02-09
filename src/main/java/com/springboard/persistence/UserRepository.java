package com.springboard.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboard.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);
}
