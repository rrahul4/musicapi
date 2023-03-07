package com.api.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.music.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username = ?1 and u.password = ?2")
	public User authenticate(String username, String password);
	
	@Query("SELECT u FROM User u where u.username = ?1")
	public User findByUsername(String username);
}
