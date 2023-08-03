package com.example.NewsWebsite.Model.Entity;

import com.example.NewsWebsite.Model.Enums.UserRoles;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity extends BaseEntity implements UserDetails {
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	Set<RolesEntity> userRolesSet;
	@OneToMany(mappedBy = "author")
	private Set<ArticleEntity> articles;
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Set<CommentEntity> comments;

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public UserEntity setComments(Set<CommentEntity> comments) {
		this.comments = comments;
		return this;
	}

	public Set<ArticleEntity> getArticles() {
		return articles;
	}

	public UserEntity setArticles(Set<ArticleEntity> articles) {
		this.articles = articles;
		return this;
	}

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

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserEntity setUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//todo this a stub
		return this.userRolesSet;
	}

	public String getPassword() {
		return password;
	}

	public UserEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public Set<RolesEntity> getUserRolesSet() {
		return userRolesSet;
	}

	public UserEntity setUserRolesSet(Set<RolesEntity> userRolesList) {
		this.userRolesSet = userRolesList;
		return this;
	}

	public UserEntity(String firstName, String lastName, String username, String password, Set<RolesEntity> userRolesSet) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userRolesSet = userRolesSet;
	}
	public UserEntity() {}
}
