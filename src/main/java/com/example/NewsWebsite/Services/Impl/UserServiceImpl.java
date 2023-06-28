package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Repositories.UserRepository;
import com.example.NewsWebsite.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void saveUser(UserEntity user){
		userRepository.save(user);
	}
}
