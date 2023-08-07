package com.example.NewsWebsite.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AboutController {
	private final String aboutText = "In this project I have tried to create a simplified version of website for news with mvc architecture.\n" +
			"In order to do so I have used SpringBoot with thymleaf.\n" +
			"All the data is stored in MySQL database. There are three\n" +
			"types of user roles: AUTHOR,ADMIN,USER. When a new User is\n" +
			"registered he is automatically given the USER user role. In";
	@ModelAttribute("text")
	public String getText (){return aboutText;}
	@GetMapping("/about")
	public String getAbout(){
		return "about";
	}
}
