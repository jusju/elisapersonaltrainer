package com.example.personaltrainer.web;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.personaltrainer.domain.UserRepository;
import com.example.personaltrainer.domain.SignupForm;
import com.example.personaltrainer.domain.User;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserRepository urepository;
	
	// REGISTRATION
	@RequestMapping(value = "registration")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "registration";
	}
	
	// CREATE NEW USER
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pswd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPswd = bc.encode(pswd);

				User newUser = new User();
				newUser.setPasswordHash(hashPswd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole("USER");
				// CHECK IF USER ALREADY EXISTS
				if (urepository.findByUsername(signupForm.getUsername()) == null) {
					urepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "error.userexists", "Username already exists");
					return "registration";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "error.passMatch", "Passwords do not match");
				return "registration";
			}
		} else {
			return "registration";
		}
		return "redirect:/login";
	}

	

}
