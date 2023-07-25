package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.Entity.ArticleEntity;
import com.example.NewsWebsite.Repositories.ArticleRepository;
import com.example.NewsWebsite.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
//	@ModelAttribute("allArticles")
//	public List<ArticleDTO> allArticles () { return  articleService.findAllArticles();}

	//test
	@ModelAttribute("allArticles")
	public List<ArticleDTO> articles (@RequestParam Integer pageNumber) {
		Page<ArticleDTO> artcilesPage = articleService.findPage(pageNumber);
		int totalPages = artcilesPage.getTotalPages();
		long totalItems = artcilesPage.getTotalElements();
		ArrayList<ArticleDTO> DTOarticles=new ArrayList<>( artcilesPage.getContent());
		return DTOarticles;
	}
	@ModelAttribute("totalPages")
	public Integer totalPages(){
		return  articleService.findPage(1).getTotalPages();
	}
	@ModelAttribute("pagesForNavigation")
	public List<Integer> pagesForNavigation(@RequestParam Integer pageNumber){
		List <Integer> pages = new ArrayList<>();
		//finding total number of pages
		int totalNumberOfPages = articleService.findPage(1).getTotalPages();
		if(pageNumber == 1 || totalNumberOfPages==0){
			//if user has requested the first page first and previous tab in navigation will have value 1
			for(int i=1;i<3;i++){
				pages.add(1);
			}
			//adding current page number
			pages.add(pageNumber);
			//logic for next and last tab depending on the number of total existing pages
			if(totalNumberOfPages == 1 || totalNumberOfPages==0){
				for(int i=1;i<3;i++){
					pages.add(1);
				}
			} else if (totalNumberOfPages==2) {
				for(int i=1;i<3;i++){
					pages.add(2);
				}
			}else{
				//adding value for "next tab" in navigation
				pages.add(2);
				pages.add(articleService.findPage(1).getTotalPages());
			}
			return pages;
		}else{
			//adding value for tab "first"
			pages.add(1);
			//adding value for tab previous
			pages.add(pageNumber-1);
			//adding cuurent page
			pages.add(pageNumber);
			//logic if user has re`uested the last page
			if(pageNumber==totalNumberOfPages){
				//adding same value for next and last tab
				pages.add(pageNumber);
				pages.add(pageNumber);
			}else{
				//check if last and next page are different
				if((pageNumber+1) == totalNumberOfPages){
					//logic if they are the same
					pages.add(pageNumber+1);
					pages.add(pageNumber+1);
				}else{
					pages.add(pageNumber+1);
					pages.add(totalNumberOfPages);
				}
			}
			return pages;
		}
	}
//	@ModelAttribute("totalPages")
//	public List<ArticleDTO> totalPages (Integer pageNumber) {
//		Page<ArticleDTO> artcilesPage = articleService.findPage(currentPage);
//		int totalPages = artcilesPage.getTotalPages();
//	}

	//test

	//initial endpoint for delivering all articles
//	@GetMapping("/")
//	public String HomePage(
//			ArrayList<ArticleDTO> allArticles,
//			RedirectAttributes redirectAttributes,
//			Model model
//	){
//		redirectAttributes.addAttribute("allArticles",allArticles);
//		return "index";
//	}
	@GetMapping("/")
	public String HomePageRedirect(){
		return "redirect:/home/1";
	}
//		@GetMapping("/home/1")
//	public String HomePage(
//			RedirectAttributes redirectAttributes,
//			Model model
//	){
//			Page<ArticleDTO> artcilesPage = articleService.findPage(1);
//			int totalPages = artcilesPage.getTotalPages();
//			long totalItems = artcilesPage.getTotalElements();
////!!!!!!!!!!!!!
//			if(artcilesPage.getTotalElements()!=0){
//				System.out.println("asdasd");
//			}
//			//!!!!
////			redirectAttributes.addAttribute("allArticles", artcilesPage.getContent().toString());
//			redirectAttributes.addAttribute("allArticles", articles(1).toString());
//			redirectAttributes.addAttribute("totalPages",totalPages);
//			redirectAttributes.addAttribute("totalItems",totalItems);
//			redirectAttributes.addAttribute("currentPage",1);
//
//			return "index";
//	}

	@GetMapping("/home/{pageNumber}")
	public String HomePageGetOnePage(
			@PathVariable("pageNumber") int currentPage,
			ArrayList<ArticleDTO> allArticles,
			RedirectAttributes redirectAttributes,
			Model model
	){
		Page<ArticleDTO> artcilesPage = articleService.findPage(currentPage);
		//int totalPages = artcilesPage.getTotalPages();
		long totalItems = artcilesPage.getTotalElements();
		List<ArticleDTO> articles= artcilesPage.getContent();
		//test
		if(currentPage==2){
			System.out.println("as");
		}
		//test

//		redirectAttributes.addFlashAttribute("allArticles",articles(currentPage).toString());
//		redirectAttributes.addAttribute("totalPages",totalPages().toString());
		redirectAttributes.addAttribute("totalItems",totalItems);
		redirectAttributes.addAttribute("currentPage",currentPage);
//		System.out.println(articles(currentPage).toString()+"########################");

		return "index";
	}
}
