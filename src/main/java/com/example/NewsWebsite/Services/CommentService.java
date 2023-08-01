package com.example.NewsWebsite.Services;

import com.example.NewsWebsite.Model.DTO.CommentDTO;

import java.util.ArrayList;


public interface CommentService {
	public void save(CommentDTO commentDTO,String articleTitile);
//
//	public Page<CommentDTO> findPage(CommentFilterDTO commentFilterDTO,String title);
	public ArrayList<CommentDTO> loadCommentsForArticle(String articleTitle);
}
