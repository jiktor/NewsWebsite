package com.example.NewsWebsite.Repositories;



import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLine implements CommandLineRunner {
	@Autowired
	UserEntityRepository userEntityRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		Set<RolesEntity> userRoles = new HashSet<>();
//		rolesRepository.save(new RolesEntity().setUserRole(UserRoles.USER));
//		rolesRepository.save(new RolesEntity().setUserRole(UserRoles.ADMIN));
		userRoles.add(rolesRepository.findByUserRole(UserRoles.USER).orElseThrow());
		userRoles.add(rolesRepository.findByUserRole(UserRoles.ADMIN).orElseThrow());
		UserEntity user = new UserEntity();
		user
				.setUsername("test")
				.setPassword(passwordEncoder.encode("test"))
				.setUserRolesSet(userRoles);

		userEntityRepository.save(user);
	}
}
