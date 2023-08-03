package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.RolesSetDTO;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

public interface UserEntityService {
	public void registerNewUser(UserDTO userDTO);
	public UserEntity findByUsernameService(String username) throws UsernameNotFoundException;
	public UserDTO findByUsername(String username) throws UsernameNotFoundException;
	public void save(UserEntity userEntity);

	Page<UserDTO> findPage(Integer pageNumber);
	@Transactional
	void deleteUserByUsername(String username);
	public Map<String,Boolean> rolesForUser(String username);
	public RolesSetDTO roles(String username);

	void updateRolesForUser(String username, RolesSetDTO roles);
}
