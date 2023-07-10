package com.example.NewsWebsite.Model.Entity;

import com.example.NewsWebsite.Model.Enums.UserRoles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "roles")
public class RolesEntity extends BaseEntity implements GrantedAuthority {
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRoles userRole;
	@ManyToMany(targetEntity = UserEntity.class, mappedBy = "userRolesSet")
	private List<UserEntity> userEntityList;

	public UserRoles getUserRole() {
		return userRole;
	}

	public RolesEntity setUserRole(UserRoles userRole) {
		this.userRole = userRole;
		return this;
	}

	public List<UserEntity> getUserEntityList() {
		return userEntityList;
	}

	public RolesEntity setUserEntityList(List<UserEntity> userEntityList) {
		this.userEntityList = userEntityList;
		return this;
	}

	@Override
	public String getAuthority() {
		return this.userRole.toString();
	}
}
