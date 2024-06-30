package com.jayanth.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayanth.blog.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	
}
