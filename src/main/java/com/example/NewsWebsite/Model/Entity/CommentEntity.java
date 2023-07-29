package com.example.NewsWebsite.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
public class CommentEntity extends BaseEntity {
	@Column
	@NotEmpty
	private String text;
	@Column
	private Date date;
	@ManyToOne
	@JoinColumn
	private ArticleEntity article;
	@ManyToOne
	@JoinColumn
	private UserEntity author;

	public UserEntity getAuthor() {
		return author;
	}

	public CommentEntity setAuthor(UserEntity author) {
		this.author = author;
		return this;
	}

	public CommentEntity(){}
	public CommentEntity(String text, Date date, ArticleEntity article) {
		this.text = text;
		this.date = date;
		this.article = article;
	}

	public String getText() {
		return text;
	}

	public CommentEntity setText(String text) {
		this.text = text;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public CommentEntity setDate(Date date) {
		this.date = date;
		return this;
	}

	public ArticleEntity getArticle() {
		return article;
	}

	public CommentEntity setArticle(ArticleEntity article) {
		this.article = article;
		return this;
	}
}
