package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Services.UserService;
import jakarta.validation.Valid;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Controller
public class RegisterControler {
	private final UserService userService;
	@Autowired
	public RegisterControler(UserService userService){
		this.userService = userService;
	}
	//retuens empty dto if page is accessed for the first time
	@ModelAttribute
	public UserDTO userDTO(){
		return new UserDTO();
	}
	@GetMapping("/register")
	public String registerPage(Model userDTO){
		return "register";
	}
	@PostMapping("/register")
	public String submitRegister(@Valid UserDTO userDTO,
								 BindingResult bindingResult,
								 RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()){
			redirectAttributes.
					addFlashAttribute("userDTO", userDTO).
					addFlashAttribute("org.springframework.validation.BindingResult.userDTO",bindingResult);
			return "redirect:/register";
		}
		//If there are no errors the user is set role User.
		//If a new user has to have different roles than user, an admin should grant them to him manually
		//TODO: Implement granting roles for user
		userDTO.setUserRolesSet(new HashSet<UserRoles>(Arrays.asList(UserRoles.USER)));
		userService.registerNewUser(userDTO);
		return "redirect:/";
	}
}
