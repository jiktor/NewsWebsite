package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Model.Entity.CommentEntity;

public interface CommentService {
	public void save(CommentDTO commentDTO,String articleTitile);
}
