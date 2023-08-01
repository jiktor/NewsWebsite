package com.example.NewsWebsite.Services.Impl;

import com.example.NewsWebsite.Conf.UserMapper;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Repositories.ArticleRepository;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.UserEntityService;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final UserMapper userMapper;
	private final UserEntityService userEntityService;
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository, UserMapper userMapper, UserEntityService userEntityService) {
		this.articleRepository = articleRepository;
		this.userMapper = userMapper;
		this.userEntityService = userEntityService;
	}
	@Override
	public void saveArticle(ArticleDTO articleDTO, String username) {
		//articleDTO.setTitle(formatTitle(articleDTO.getTitle()));
		articleDTO.setText(formatTextOfArticle(articleDTO.getText()));
		ArticleEntity articleEntity = userMapper.DTOToArticle(articleDTO);
		articleEntity.setAuthor(userEntityService.findByUsernameService(username));
		articleRepository.save(articleEntity);
	}

	@Override
	public List<ArticleDTO> findAllArticles() {
		List<ArticleEntity> list = articleRepository.findAll();
		List<ArticleDTO> articleDTOS = new ArrayList<>();
		for (ArticleEntity a:list) {
			articleDTOS.add(userMapper.articleToDTO(a));
		}
		for(int i =0; i<articleDTOS.size();i++){
			articleDTOS.get(i)
					.setBase64Image(Base64.getEncoder().encodeToString(articleDTOS.get(i).getImages()));
		}
		return articleDTOS;
	}

	@Override
	public Page<ArticleDTO> findPage(int pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber-1,3, Sort.by("dateOfPublishing").descending());
		 //articleRepository.findAll(pageable);

		Page<ArticleEntity> entities = articleRepository.findAll(pageable);
//		if(entities.getTotalElements()!=0){
//			System.out.println("asd");
//		}
		Page<ArticleDTO> dtoPage = entities.map(new Function<ArticleEntity, ArticleDTO>()  {
			@Override
			public ArticleDTO apply(ArticleEntity entity) {
				ArticleDTO dto = new ArticleDTO();
				return userMapper.articleToDTO(entity);
			}
		});
		//converting photos from dao entity to dto entity

		//
		return dtoPage;
	}

	@Override
	public ArticleDTO findDTOByTitle(String title) {
		//TODO трябва да се добави проверка в котролера дали е намерена статия
		ArticleEntity articleEntity = articleRepository.findByTitle(title).orElse(null);
		return userMapper.articleToDTO(articleEntity);
	}

	@Override
	public ArticleEntity findByTitle(String title) {
		return articleRepository.findByTitle(title).orElse(null);
	}
	//Adds symbols for new lines before the text of the article is saved
	public String formatTextOfArticle(String text){
		final int charactersOnLine = 185;
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=1;;i++){
			if(text.length()>=charactersOnLine*i+charactersOnLine) {
				if(i!=1) {
					String currentLine = text.substring(charactersOnLine * i, charactersOnLine * i + charactersOnLine);
					if (currentLine.charAt(currentLine.length() - 1) != ' ') {
						currentLine += '-';
					}
					currentLine += '\n';
					stringBuffer.append(currentLine);
				}else{
					String currentLine = text.substring(0, charactersOnLine * i + charactersOnLine);
					if (currentLine.charAt(currentLine.length() - 1) != ' ') {
						currentLine += '-';
					}
					currentLine += '\n';
					stringBuffer.append(currentLine);
				}
			}else{
				if(text.length()>charactersOnLine){
					stringBuffer.append(text.substring(charactersOnLine * i, text.length()));
					break;
				}else{
					stringBuffer.append(text.substring(0, text.length()));
					break;
				}
			}
		}
		return stringBuffer.toString();
	}
	public String formatTitle(String title){
		if(title.length()>50){
			String toBeReturned = "";
			final int lineSize = 50;
			boolean end = false;
			for(int y=0;y<title.length();y++){
				if(y%50==0&&y!=0){
					toBeReturned+='\n';
				}
				toBeReturned+=title.charAt(y);
			}
			return toBeReturned;
		}else{
			return title;
		}
	}
}
