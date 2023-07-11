package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserEntityService {
	public void registerNewUser(UserDTO userDTO);
	public UserEntity findByUsernameService(String username) throws UsernameNotFoundException;
	public void save(UserEntity userEntity);
}
