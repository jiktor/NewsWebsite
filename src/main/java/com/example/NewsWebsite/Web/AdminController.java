package com.example.NewsWebsite.Web;

import com.example.NewsWebsite.Model.DTO.ArticleDTO;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
	private final UserEntityService userService;
	@Autowired
	public AdminController(UserEntityService userService) {this.userService = userService;}
	@ModelAttribute("users")
	public ArrayList<UserDTO> users(@RequestParam(defaultValue = "1") Integer pageNumber){
		Page<UserDTO> userPage = userService.findPage(pageNumber);
		int totalPages = userPage.getTotalPages();
		long totalItems = userPage.getTotalElements();
		ArrayList<UserDTO> DTOusers=new ArrayList<>( userPage.getContent());
		return DTOusers;
	}
	@ModelAttribute("totalPages")
	public Integer totalPages(){
		return  userService.findPage(1).getTotalPages();
	}
	@ModelAttribute("pagesForNavigation")
	public List<Integer> pagesForNavigation(@RequestParam(defaultValue = "1") Integer pageNumber){
		List <Integer> pages = new ArrayList<>();
		//finding total number of pages
		int totalNumberOfPages = userService.findPage(1).getTotalPages();
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
				pages.add(userService.findPage(1).getTotalPages());
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
	@GetMapping("/admin")
	public String getLoginPage(){
		return "admin";
	}
	@GetMapping("/admin/deleteUser")
	public String deleteUser(@RequestParam String username,
							Principal principal){
		if(!principal.getName().equals(username)) {
			userService.deleteUserByUsername(username);
			return "redirect:/admin";
		}else{
			throw new RuntimeException("Admin cannot delete his profile while logged in it");
		}
	}
}
