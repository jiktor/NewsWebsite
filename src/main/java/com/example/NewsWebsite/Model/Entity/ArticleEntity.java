package com.example.NewsWebsite.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.bv.time.future.FutureValidatorForLocalDateTime;

import java.util.Date;
import java.util.List;
@Entity
public class ArticleEntity extends BaseEntity{
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	private String text;
	@Column
	private Date dateOfPublishing;
	@ManyToOne
	@JoinColumn(nullable = false)
	private UserEntity author;
	@Column
	@Lob
	private List<byte[]> images;
	public ArticleEntity(){}
	public ArticleEntity(String name, String text, Date dateOfPublishing, UserEntity author, List<byte[]> images) {
		this.name = name;
		this.text = text;
		this.dateOfPublishing = dateOfPublishing;
		this.author = author;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public ArticleEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getText() {
		return text;
	}

	public ArticleEntity setText(String text) {
		this.text = text;
		return this;
	}

	public Date getDateOfPublishing() {
		return dateOfPublishing;
	}

	public ArticleEntity setDateOfPublishing(Date dateOfPublishing) {
		this.dateOfPublishing = dateOfPublishing;
		return this;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public ArticleEntity setAuthor(UserEntity author) {
		this.author = author;
		return this;
	}

	public List<byte[]> getImages() {
		return images;
	}

	public ArticleEntity setImages(List<byte[]> images) {
		this.images = images;
		return this;
	}
}
