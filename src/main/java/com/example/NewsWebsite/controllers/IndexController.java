package com.example.NewsWebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String HomePage(){
		return "index";
	}
	@GetMapping("/register")
	public String registerPage(){
		return "register";
	}
}
