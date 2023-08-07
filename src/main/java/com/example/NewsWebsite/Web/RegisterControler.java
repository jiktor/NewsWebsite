package com.example.NewsWebsite.Web;

import ch.qos.logback.core.model.Model;
import com.example.NewsWebsite.Model.DTO.UserDTO;
import com.example.NewsWebsite.Model.Entity.RolesEntity;
import com.example.NewsWebsite.Model.Entity.UserEntity;
import com.example.NewsWebsite.Model.Enums.UserRoles;
import com.example.NewsWebsite.Services.RolesService;
import com.example.NewsWebsite.Services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	@ModelAttribute("userDTO")
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
								 RedirectAttributes redirectAttributes,
								 Model model) {
		//check if username is avaivable
		try {
		UserEntity userEntity = userEntityService.findByUsernameService(userDTO.getUsername());
		if (userEntity != null) {
			redirectAttributes.
					addFlashAttribute("usernameTaken", true).
					addFlashAttribute("userDTO", userDTO).
					addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
			return "redirect:/register";
		}
	}catch (UsernameNotFoundException e){
			//if username is not found the validiation proccess can continue
		}
		//Check for errors caused by dto annotations
		if(bindingResult.hasErrors()){
			redirectAttributes.
					addFlashAttribute("userDTO", userDTO).
					addFlashAttribute("org.springframework.validation.BindingResult.userDTO",bindingResult);
			return "redirect:/register";
		}
		//Check for matching passwords
		if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
			redirectAttributes.
					addFlashAttribute("passwordsNotMatching", true).
					addFlashAttribute("userDTO", userDTO).
					addFlashAttribute("org.springframework.validation.BindingResult.userDTO",bindingResult);
			return "redirect:/register";
		}
		//If there are no errors the user is set role User.
		//If a new user has to have different roles than user, an admin should grant them to him manually
		RolesEntity roleForUser = rolesService.findByUserRole(UserRoles.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("sda"));
		Set<RolesEntity> rolesEntitySet = new HashSet<RolesEntity>();
		rolesEntitySet.add(roleForUser);
		userDTO.setUserRolesSet(rolesEntitySet);
		userEntityService.registerNewUser(userDTO);
		return "redirect:/home/1";
	}
}
