package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
	public List<ArticleEntity> findAll();
}
