package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Repositories.UserRepository;
import com.example.NewsWebsite.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	public void saveUser(UserEntity user){
		userRepository.save(user);
	}

	@Override
	public void registerNewUser(UserDTO userDTO) {
		if(userRepository.findByUsername(userDTO.getUsername())==null){
			userRepository.save(userMapper.DTOToUser(userDTO));
		}else{
			//TODO logic if username already exists
		}
	}
}
