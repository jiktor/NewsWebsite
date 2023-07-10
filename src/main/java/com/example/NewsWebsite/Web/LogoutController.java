package com.example.NewsWebsite.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String logoutPage(){
		return "logout";
	}
	@PostMapping("/logout")
	public String postLogoutPage(){
		return "redirect:/logout";
	}
}
