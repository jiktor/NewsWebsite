package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Services.ArticleService;
import com.example.NewsWebsite.Services.CommentService;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class LoadArticleController {
	private final ArticleService articleService;
	private final UserEntityService userEntityService;
	private final CommentService commentService;
	@Autowired
	public LoadArticleController(ArticleService articleService, UserEntityService userEntityService, CommentService commentService){
		this.articleService=articleService;
		this.userEntityService = userEntityService;
		this.commentService = commentService;
	}
	@ModelAttribute("commentDTO")
	public CommentDTO commentDTO(){
		return new CommentDTO();
	}
	@ModelAttribute("article")
	public ArticleDTO article(@RequestParam String title){
		return articleService.findDTOByTitle(title);
	}
	@GetMapping("/article")
	public String loadArticle(){
		return "loadArticle";
	}
	//TODO да се добавят проверки дали всички полета са попълнени при запазване на нов коментар
	@PostMapping("/article")
	public String saveComment(@RequestParam String title,
							@Valid CommentDTO commentDTO,
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes,
							Model model,
							Principal principal){
		commentDTO.setAuthor(userEntityService.findByUsernameService(principal.getName()));
		LocalDateTime myLocalDateTime =  LocalDateTime.now();
		commentDTO.setDate(java.sql.Timestamp.valueOf( myLocalDateTime ));
		commentService.save(commentDTO,title);
		String redirectURL = "redirect:/article?title="+title;
		return redirectURL;
	}
}
