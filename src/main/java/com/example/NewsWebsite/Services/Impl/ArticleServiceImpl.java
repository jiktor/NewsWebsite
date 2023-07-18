package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Repositories.ArticleRepository;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<ArticleDTO> findAllArticles() {
		List<ArticleEntity> list = articleRepository.findAll();
		List<ArticleDTO> articleDTOS = new ArrayList<>();
		for (ArticleEntity a:list) {
			articleDTOS.add(userMapper.articleToDTO(a));
		}
		for(int i =0; i<articleDTOS.size();i++){
			articleDTOS.get(i)
					.setBase64Image(Base64.getEncoder().encodeToString(articleDTOS.get(i).getImages()));
		}
		return articleDTOS;
	}
}
