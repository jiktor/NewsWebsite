package com.example.NewsWebsite.Web;

import com.example.NewsWebsite.Model.DTO.CommentDTO;
import com.example.NewsWebsite.Services.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentsController {
	private final CommentService commentService;
	public CommentsController(CommentService commentService) {this.commentService = commentService;}
	@ModelAttribute("comments")
	public List<CommentDTO> comments(@RequestParam String title){
		return commentService.loadCommentsForArticle(title);
	}
	@GetMapping("article/comment")
	public String loadCommentsForArticle(RedirectAttributes redirectAttributes,
										 @Valid ArrayList<CommentDTO> comments
										 ){
		//redirectAttributes.addFlashAttribute(comments);
		return "CommentsForArticle";
	}
}
