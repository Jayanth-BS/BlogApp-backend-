package com.jayanth.blog.services.impl;

import com.jayanth.blog.repositories.*;
import com.jayanth.blog.repositories.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayanth.blog.entities.User;
import com.jayanth.blog.payload.UserDto;
import com.jayanth.blog.services.UserService;
import com.jayanth.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userdto) {
//		 TODO Auto-generated method stub
		User user = this.dtoToUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.usertoDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User"," id ",id));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);
//		User user = new User();
//		user.setId(userdto.getId());
//		user.setEmail(userdto.getEmail());
//		user.setName(userdto.getName());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	private UserDto usertoDto(User user) {
		UserDto userdto = this.modelMapper.map(user, UserDto.class);
//		UserDto userdto = new UserDto();
//		userdto.setId(user.getId());
//		userdto.setEmail(user.getEmail());
//		userdto.setName(user.getName());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
	}

}
