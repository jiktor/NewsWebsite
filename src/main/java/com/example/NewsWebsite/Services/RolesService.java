package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;

import java.util.Optional;

public interface RolesService {
	Optional<RolesEntity> findByUserRole(UserRoles user);
}
