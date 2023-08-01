package com.example.NewsWebsite.Model.DTO;

import com.example.NewsWebsite.Model.Entity.CommentEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class ArticleDTO {
	@NotEmpty
	@Size(max = 125)
	private String title;
	@NotEmpty
	private String text;
	private byte[] images;
	private UserEntity author;

	private Date dateOfPublishing;
	private String base64Image;
	private List<CommentEntity> commentEntities;

	public List<CommentEntity> getCommentEntities() {
		return commentEntities;
	}

	public ArticleDTO setCommentEntities(List<CommentEntity> commentEntities) {
		this.commentEntities = commentEntities;
		return this;
	}

	public ArticleDTO(String title, String text, byte[] images, UserEntity author, Date dateOfPublishing) {
		this.title = title;
		this.text = text;
		this.images = images;
		this.author = author;
		this.dateOfPublishing = dateOfPublishing;
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

	public UserEntity getAuthor() {
		return author;
	}

	public ArticleDTO setAuthor(UserEntity author) {
		this.author = author;
		return this;
	}

	public Date getDateOfPublishing() {
		return dateOfPublishing;
	}

	public ArticleDTO setDateOfPublishing(Date dateOfPublishing) {
		this.dateOfPublishing = dateOfPublishing;
		return this;
	}
}
