package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
