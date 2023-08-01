package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Model.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	//Page<CommentEntity> findPaginatedComment(CommentFilterDTO commentFilterDTO, Pageable pageable);
	List<CommentEntity> findByArticle(ArticleEntity article);
}
