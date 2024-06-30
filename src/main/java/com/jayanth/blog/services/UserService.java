package com.jayanth.blog.services;

import java.util.List;

import com.jayanth.blog.payload.UserDto;
import java.util.List;
import com.jayanth.blog.entities.User;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer id);
}
