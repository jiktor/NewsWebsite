package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;

import java.util.List;

public interface ArticleService {
	public void saveArticle(ArticleDTO articleDTO, String username);
	public List<ArticleDTO> findAllArticles();
}
