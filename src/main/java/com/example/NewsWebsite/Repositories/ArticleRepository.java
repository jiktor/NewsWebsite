package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
	public List<ArticleEntity> findAll();
	public Optional<ArticleEntity> findByTitle(String title);
}
