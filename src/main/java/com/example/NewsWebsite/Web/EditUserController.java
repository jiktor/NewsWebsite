package com.example.NewsWebsite.Web;

import com.example.NewsWebsite.Model.DTO.RolesSetDTO;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EditUserController {
	private final UserEntityService userService;
	@Autowired
	public EditUserController(UserEntityService userService) {this.userService = userService;}
	@ModelAttribute("userDTO")
	public UserDTO editUser(@RequestParam String username){
		return userService.findByUsername(username);
	}
	@ModelAttribute("rolesModelAttribute")
	public RolesSetDTO roles(@RequestParam String username){return userService.roles(username);}
	@ModelAttribute("rolesSetDTO")
	public RolesSetDTO rolesSetDTO(){
		return new RolesSetDTO();
	}
	@GetMapping("/admin/editUser")
	public String editUser(
			Principal principal){
		return "editUser";
	}
	@PostMapping("/admin/editUser")
	public String editRoles(
			RolesSetDTO roles,
			@RequestParam String username,
			Principal principal){
		System.out.println(roles.getIsUser());
		System.out.println(roles.getIsAdmin());
		System.out.println(roles.getIsAuthor());
		userService.updateRolesForUser(username,roles);
		return "redirect:/home/1?pageNumber=1";
	}
}
