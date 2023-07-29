package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Model.Entity.CommentEntity;
import com.example.NewsWebsite.Repositories.CommentRepository;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final ArticleService articleService;
	private final UserMapper userMapper;
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService, UserMapper userMapper) {
		this.commentRepository = commentRepository;
		this.articleService = articleService;
		this.userMapper = userMapper;
	}
	public void 	save(CommentDTO commentDTO, String articleTitle){
		CommentEntity commentEntity = userMapper.DTOToComment(commentDTO);
		commentEntity.setArticle(articleService.findByTitle(articleTitle));
		commentRepository.save(commentEntity);
	}
}
