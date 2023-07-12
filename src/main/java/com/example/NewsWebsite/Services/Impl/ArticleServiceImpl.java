package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Repositories.ArticleRepository;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final UserMapper userMapper;
	private final UserEntityService userEntityService;
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository, UserMapper userMapper, UserEntityService userEntityService) {
		this.articleRepository = articleRepository;
		this.userMapper = userMapper;
		this.userEntityService = userEntityService;
	}
	@Override
	public void saveArticle(ArticleDTO articleDTO, String username) {
		ArticleEntity articleEntity = userMapper.DTOToArticle(articleDTO);
		articleEntity.setAuthor(userEntityService.findByUsernameService(username));
		articleRepository.save(articleEntity);
	}
}
