package com.example.NewsWebsite.Model.DTO;

public class RolesSetDTO {
	private Boolean isAdmin;
	private Boolean isAuthor;
	private Boolean isUser;

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public RolesSetDTO setIsAdmin(Boolean admin) {
		isAdmin = admin;
		return this;
	}

	public Boolean getIsAuthor() {
		return isAuthor;
	}

	public RolesSetDTO setIsAuthor(Boolean author) {
		isAuthor = author;
		return this;
	}

	public Boolean getIsUser() {
		return isUser;
	}

	public RolesSetDTO setIsUser(Boolean user) {
		isUser = user;
		return this;
	}
}
