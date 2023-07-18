package com.example.NewsWebsite.Model.DTO;

import com.example.NewsWebsite.Model.Entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

public class ArticleDTO {
	@NotEmpty
	private String title;
	@NotEmpty
	private String text;
	private byte[] images;
	private UserEntity userEntity;

	private Date dateOfCreation;
	private String base64Image;

	public ArticleDTO(String title, String text, byte[] images, UserEntity userEntity, Date dateOfCreation) {
		this.title = title;
		this.text = text;
		this.images = images;
		this.userEntity = userEntity;
		this.dateOfCreation = dateOfCreation;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public ArticleDTO setBase64Image(String base64Image) {
		this.base64Image = base64Image;
		return this;
	}

	public ArticleDTO(){}
	public String getTitle() {
		return title;
	}

	public ArticleDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getText() {
		return text;
	}

	public ArticleDTO setText(String text) {
		this.text = text;
		return this;
	}

	public byte[] getImages() {
		return images;
	}

	public ArticleDTO setImages(byte[] images) {
		this.images = images;
		return this;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public ArticleDTO setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
		return this;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public ArticleDTO setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
		return this;
	}
}
