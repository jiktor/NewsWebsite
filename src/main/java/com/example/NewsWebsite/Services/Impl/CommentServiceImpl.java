package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Model.Entity.CommentEntity;
import com.example.NewsWebsite.Repositories.CommentRepository;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
	public void  save(CommentDTO commentDTO, String articleTitle){
		CommentEntity commentEntity = userMapper.DTOToComment(commentDTO);
		commentEntity.setArticle(articleService.findByTitle(articleTitle));
		commentRepository.save(commentEntity);
	}
	@Override
	public ArrayList<CommentDTO> loadCommentsForArticle(String articleTitle) {
		ArrayList<CommentEntity> entities = (ArrayList<CommentEntity>)
				commentRepository.findByArticle(articleService.findByTitle(articleTitle));

		ArrayList<CommentDTO> dtoArrayList = new ArrayList<>();
		for(CommentEntity entity : entities){
			dtoArrayList.add(userMapper.CommentToDTO(entity));
		}
		return dtoArrayList;
	}

//	@Override
//	public Page<CommentDTO> findPage(CommentFilterDTO commentFilterDTO, String title) {
//		int pageNumber = commentFilterDTO.getPageNumber();
//		Page<CommentEntity> pageOfEntity = commentRepository.
//				findPaginatedComment(commentFilterDTO,
//						PageRequest.of(pageNumber - 1, commentFilterDTO.getPageSize()));
//
//		Page<CommentDTO> dtoPage = pageOfEntity.map(new Function<CommentEntity, CommentDTO>()  {
//			@Override
//			public CommentDTO apply(CommentEntity entity) {
//				CommentDTO dto = new CommentDTO();
//				return userMapper.CommentToDTO(entity);
//			}
//		});
//		return dtoPage;
//	}
}
