package com.example.NewsWebsite.Model.Entity;

import com.example.NewsWebsite.Model.Enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Set;

@Entity
public class UserEntity extends BaseEntity{
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column
	Set<UserRoles> userRolesList;

	public String getFirstName() {
		return firstName;
	}

	public UserEntity setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserEntity setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserEntity setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public Set<UserRoles> getUserRolesList() {
		return userRolesList;
	}

	public UserEntity setUserRolesList(Set<UserRoles> userRolesList) {
		this.userRolesList = userRolesList;
		return this;
	}
}
