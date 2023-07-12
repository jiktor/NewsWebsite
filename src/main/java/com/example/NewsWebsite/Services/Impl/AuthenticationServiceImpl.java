package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Services.AuthenticationService;
import com.example.NewsWebsite.Services.RolesService;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserMapper userMapper;
	private final UserEntityService userEntityService;
	private final RolesService rolesService;
	private final PasswordEncoder passwordEncoder;
@Autowired
	public AuthenticationServiceImpl(UserMapper userMapper, UserEntityService userEntityService, RolesService rolesService, PasswordEncoder passwordEncoder) {
	this.userMapper = userMapper;
	this.userEntityService = userEntityService;
		this.rolesService = rolesService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void registerNewUser(UserDTO userDTO) {
	try {
		UserEntity US = userEntityService.findByUsernameService(userDTO.getUsername());
		//If no exception is thrown -> username is already used
		throw new RuntimeException("username is already used");//todo replace with custom exception
		//If the username is from user DTO is free userEntityService.findByUsernameService will throw an exception
	}catch (UsernameNotFoundException exception){
		UserEntity userEntity = userMapper.DTOToUser(userDTO);
		System.out.println(userEntity.getPassword() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		System.out.println(userEntity.getPassword() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		//All users are given role "USER" when registered. Only a User with role "ADMIN" can grant them other roles.
		RolesEntity rolesEntity = rolesService.findByUserRole(UserRoles.ROLE_USER).orElseThrow(() -> new RuntimeException("User role was not added"));
		Set<RolesEntity> set = new HashSet<>();
		set.add(rolesEntity);
		userEntity.setUserRolesSet(set);
		userEntityService.save(userEntity);
	}
	}

}
