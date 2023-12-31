package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class NewArticleController {
	private final ArticleService articleService;
@Autowired
	public NewArticleController(ArticleService articleService){
	this.articleService = articleService;
	}
	@ModelAttribute("articleDTO")
	public ArticleDTO articleDTO(){
		return new ArticleDTO();
	}
	@GetMapping("/newArticle")
	public String getCreateArticlePage(){
		return "newArticle";
	}
	@PostMapping("/newArticle")
	public String createArticle(@Valid ArticleDTO articleDTO,
								BindingResult bindingResult,
								RedirectAttributes redirectAttributes,
								Model model,
								Principal principal,
								@RequestParam("image") MultipartFile multipartFile) throws IOException {
	if(bindingResult.hasErrors()){
		redirectAttributes
				.addFlashAttribute("articleDTO",articleDTO)
				.addFlashAttribute("org.springframework.validation.BindingResult.articleDTO",bindingResult);
		return "redirect:/newArticle";
	}
	LocalDateTime myLocalDateTime =  LocalDateTime.now();
	articleDTO.setDateOfPublishing(java.sql.Timestamp.valueOf( myLocalDateTime ));
	articleDTO.setImages(multipartFile.getBytes());
	articleService.saveArticle(articleDTO, principal.getName());
	//todo: make a html for saved article
	return "redirect:/home/1?pageNumber=1";
	}
}
