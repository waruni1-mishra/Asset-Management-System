package com.codewithwaruni.asset.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithwaruni.asset.exception.*;
import com.codewithwaruni.asset.entities.User;
import com.codewithwaruni.asset.payloads.UserDto;
import com.codewithwaruni.asset.repositories.UserRepo;
import com.codewithwaruni.asset.services.UserService;
@Service
public class impl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	/*@Override
	public UserDto registerNewUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User" , "Id" , userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setConfirm_password(userDto.getConfirm_password());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 =this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User" , "Id" , userId));
		
		// TODO Auto-generated method stub
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users =this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User" , "Id" , userId));
		this.userRepo.delete(user);
		// TODO Auto-generated method stub

	}
	public User dtoToUser(UserDto userDto) 
	{
		User user= this.modelMapper.map(userDto, User.class);

		return user;
	}
	public UserDto userToDto(User user) 
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

}
