package com.example.NewsWebsite.Model.DTO;

import com.example.NewsWebsite.Model.Entity.RolesEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.util.Set;

public class UserDTO {

	private String firstName;
	private String lastName;
	@NonNull
	@NotEmpty
	private String username;
	@NotEmpty
	@NonNull
	private String password;
	private String confirmPassword;
	private Set<RolesEntity> userRolesSet;

	public String getPassword() {
		return password;
	}

	public UserDTO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public UserDTO setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserDTO setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserDTO setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserDTO setUsername(String username) {
		this.username = username;
		return this;
	}

	public Set<RolesEntity> getUserRolesSet() {
		return userRolesSet;
	}

	public UserDTO setUserRolesSet(Set<RolesEntity> userRolesSet) {
		this.userRolesSet = userRolesSet;
		return this;
	}
}
