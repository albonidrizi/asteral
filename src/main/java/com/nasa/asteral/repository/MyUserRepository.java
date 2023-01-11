package com.nasa.asteral.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.asteral.model.db.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	
	Optional<MyUser> findUserByUsernameIgnoreCase(String username);
	
}
