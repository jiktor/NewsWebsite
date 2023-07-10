package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Repositories.UserEntityRepository;
import com.example.NewsWebsite.Services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl implements UserEntityService, UserDetailsService {
	private final UserEntityRepository userEntityRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public UserEntityServiceImpl(UserEntityRepository userEntityRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userEntityRepository = userEntityRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void registerNewUser(UserDTO userDTO) {
		try{
			findByUsernameService(userDTO.getUsername());//throws exception if there is no user with such username
			//if there is no exception -> username is taken
			//todo logic if username is taken
		}catch (UsernameNotFoundException ex){
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			userEntityRepository.save(userMapper.DTOToUser(userDTO));
		}
	}
	@Override
	public UserEntity findByUsernameService(String username) {
			return userEntityRepository.findByUsername(username)
					.orElseThrow(()->new UsernameNotFoundException("user with username: "+username+" does not exist"));
	}

	@Override
	public void save(UserEntity userEntity) {
		userEntityRepository.save(userEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userEntityRepository.findByUsername(username)
				.orElseThrow();
	}
}
