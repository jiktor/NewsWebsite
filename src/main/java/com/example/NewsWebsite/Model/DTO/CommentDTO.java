package com.example.NewsWebsite.Model.DTO;

import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class CommentDTO {
	private String text;
	private Date date;
	private ArticleEntity article;
	private UserEntity author;

	public UserEntity getAuthor() {
		return author;
	}

	public CommentDTO setAuthor(UserEntity author) {
		this.author = author;
		return this;
	}

	public CommentDTO(){}
	public CommentDTO(String text, Date date, ArticleEntity article) {
		this.text = text;
		this.date = date;
		this.article = article;
	}

	public String getText() {
		return text;
	}

	public CommentDTO setText(String text) {
		this.text = text;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public CommentDTO setDate(Date date) {
		this.date = date;
		return this;
	}

	public ArticleEntity getArticle() {
		return article;
	}

	public CommentDTO setArticle(ArticleEntity article) {
		this.article = article;
		return this;
	}
}
