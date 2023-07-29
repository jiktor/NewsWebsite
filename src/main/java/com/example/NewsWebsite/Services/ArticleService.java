package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
	public void saveArticle(ArticleDTO articleDTO, String username);

	public List<ArticleDTO> findAllArticles();

	public Page<ArticleDTO> findPage(int pageNumber);
	public ArticleDTO findDTOByTitle(String title);
	public ArticleEntity findByTitle(String title);

}