package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Services.RolesService;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterControler {
	private final UserEntityService userEntityService;
	private final RolesService rolesService;
	@Autowired
	public RegisterControler(UserEntityService userEntityService, RolesService rolesService){
		this.userEntityService = userEntityService;
		this.rolesService = rolesService;
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
		RolesEntity roleForUser = rolesService.findByUserRole(UserRoles.USER)
				.orElseThrow(() -> new RuntimeException("sda"));
		Set<RolesEntity> rolesEntitySet = new HashSet<RolesEntity>();
		rolesEntitySet.add(roleForUser);
		userDTO.setUserRolesSet(rolesEntitySet);
		userEntityService.registerNewUser(userDTO);
		return "redirect:/";
	}
}
