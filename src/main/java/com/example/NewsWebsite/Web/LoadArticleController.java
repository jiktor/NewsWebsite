package com.example.NewsWebsite.Web;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoadArticleController {
	private final ArticleService articleService;
	@Autowired
	public LoadArticleController(ArticleService articleService){
		this.articleService=articleService;
	}
	@ModelAttribute("article")
	public ArticleDTO article(@RequestParam String title){
		return articleService.findByTitle(title);
	}
	@GetMapping("/article")
	public String loadArticle(){
		return "loadArticle";
	}
}
