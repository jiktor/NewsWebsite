package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.RolesSetDTO;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Repositories.UserEntityRepository;
import com.example.NewsWebsite.Services.RolesService;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class UserEntityServiceImpl implements UserEntityService, UserDetailsService {
	private final RolesService rolesService;
	private final UserEntityRepository userEntityRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public UserEntityServiceImpl(RolesService rolesService, UserEntityRepository userEntityRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.rolesService = rolesService;
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
	//TODO На някои места този сървис се вика директно в контролер -> трявба в контролерите да се ползват само DTO обекти
	@Override
	public UserEntity findByUsernameService(String username) throws UsernameNotFoundException{
			return userEntityRepository.findByUsername(username)
					.orElseThrow(()->new UsernameNotFoundException("user with username: "+username+" does not exist"));
	}

	@Override
	public UserDTO findByUsername(String username) throws UsernameNotFoundException {
		return userMapper.UserToDTO(findByUsernameService(username));
	}

	@Override
	public void save(UserEntity userEntity) {
		userEntityRepository.save(userEntity);
	}

	@Override
	public Page<UserDTO> findPage(Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1,5);
		Page<UserEntity> userEntities = userEntityRepository.findAll(pageable);

		Page<UserDTO> userDTOS = userEntities.map(new Function<UserEntity, UserDTO>()  {
			@Override
			public UserDTO apply(UserEntity entity) {
				UserDTO dto = new UserDTO();
				return userMapper.UserToDTO(entity);
			}
		});
		return userDTOS;
	}
	@Transactional
	@Override
	public void deleteUserByUsername(String username) {
		userEntityRepository.deleteByUsername(username);
	}

	@Override
	public Map<String, Boolean> rolesForUser(String username) {
		UserEntity user = findByUsernameService(username);
		Map<String,Boolean>roles = new HashMap<>();
		roles.put("ROLE_ADMIN",false);
		roles.put("ROLE_AUTHOR",false);
		roles.put("ROLE_USER",false);
		for(RolesEntity role : user.getUserRolesSet()){
			roles.put(role.getUserRole().toString(),true);
		}
		return roles;
	}

	@Override
	public RolesSetDTO roles(String username) {
		UserEntity user = userEntityRepository.findByUsername(username).orElse(null);
		RolesSetDTO rolesSetDTO = new RolesSetDTO();
		rolesSetDTO.setIsUser(false).setIsAdmin(false).setIsAuthor(false);

		Set<String>usersRoles = new HashSet<>();

		Iterator<RolesEntity> rolesIterator = user.getUserRolesSet().stream().iterator();
		while(rolesIterator.hasNext()){
			usersRoles.add(rolesIterator.next().getUserRole().toString());
		}

		if(usersRoles.contains("ROLE_ADMIN")){
			rolesSetDTO.setIsAdmin(true);
		}
		if(usersRoles.contains("ROLE_USER")){
			rolesSetDTO.setIsUser(true);
		}
		if(usersRoles.contains("ROLE_AUTHOR")){
			rolesSetDTO.setIsAuthor(true);
		}
		return rolesSetDTO;
	}

	@Override
	public void updateRolesForUser(String username, RolesSetDTO roles) {
		UserEntity user = userEntityRepository.findByUsername(username).orElse(null);
		Set <RolesEntity> newRoles = new HashSet<>();
		if(roles.getIsUser()==true){
			newRoles.add(rolesService.findByUserRole(UserRoles.ROLE_USER)
										.orElseThrow(()-> new RuntimeException("Couldn't fetch RolesEntity object when updating roles")));
		}if(roles.getIsAdmin()==true){
			newRoles.add(rolesService.findByUserRole(UserRoles.ROLE_ADMIN)
					.orElseThrow(()-> new RuntimeException("Couldn't fetch RolesEntity object when updating roles")));
		}if(roles.getIsAuthor()==true){
			newRoles.add(rolesService.findByUserRole(UserRoles.ROLE_AUTHOR)
					.orElseThrow(()-> new RuntimeException("Couldn't fetch RolesEntity object when updating roles")));
		}
		user.setUserRolesSet(newRoles);
		userEntityRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userEntityRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException());
	}
}
