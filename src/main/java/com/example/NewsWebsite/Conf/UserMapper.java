package com.example.NewsWebsite.Conf;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Model.Entity.CommentEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class UserMapper {

	private ModelMapper modelMapper = new ModelMapper();
	public UserEntity DTOToUser(UserDTO userDTO){
		return modelMapper.map(userDTO, UserEntity.class);
	}
	public ArticleEntity DTOToArticle (ArticleDTO articleDTO){
		return modelMapper.map(articleDTO, ArticleEntity.class);
	}
	public CommentEntity DTOToComment (CommentDTO commentDTO){ return modelMapper.map(commentDTO,CommentEntity.class);}
	public ArticleDTO articleToDTO (ArticleEntity article){
		ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
		articleDTO
				.setBase64Image(Base64.getEncoder().encodeToString(article.getImages()));
		return articleDTO;
	}
}
