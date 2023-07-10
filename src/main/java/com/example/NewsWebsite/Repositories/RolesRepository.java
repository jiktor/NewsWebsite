package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
	public Optional<RolesEntity> findByUserRole(UserRoles userRole);
}
