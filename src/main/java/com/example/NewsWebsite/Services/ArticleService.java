package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;

public interface ArticleService {
	public void saveArticle(ArticleDTO articleDTO, String username);
}
