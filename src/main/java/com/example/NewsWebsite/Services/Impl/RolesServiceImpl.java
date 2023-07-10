package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Repositories.RolesRepository;
import com.example.NewsWebsite.Services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
	private final RolesRepository rolesRepository;
@Autowired
	public RolesServiceImpl(RolesRepository rolesRepository) {this.rolesRepository = rolesRepository;}

	@Override
	public Optional<RolesEntity> findByUserRole(UserRoles role) {
		return rolesRepository.findByUserRole(role);
	}
}
