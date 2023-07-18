package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Repositories.ArticleRepository;
import com.example.NewsWebsite.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
	private final ArticleService articleService;
	@Autowired
	public IndexController(ArticleService articleService) {
		this.articleService = articleService;
	}
	@ModelAttribute("allArticles")
	public List<ArticleDTO> allArticles () { return  articleService.findAllArticles();}

	@GetMapping("/")
	public String HomePage(
			ArrayList<ArticleDTO> allArticles,
			RedirectAttributes redirectAttributes,
			Model model
	){
		redirectAttributes.addAttribute("allArticles",allArticles);
		return "index";
	}
}
