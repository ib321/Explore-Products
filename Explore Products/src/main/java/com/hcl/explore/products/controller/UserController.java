package com.hcl.explore.products.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hcl.explore.products.model.User;
import com.hcl.explore.products.service.SecurityService;
import com.hcl.explore.products.service.UserService;
import com.hcl.explore.products.validator.UserValidator;

/**
 * 
 * @author Indian Bittu
 *
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
        userValidator.validate(userForm, result);
        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
		redirectAttributes.addFlashAttribute("message", "Congratulations! You have successfully registered as <strong>"
				+ userForm.getUsername() + "</strong>. Please log in to continue.");
        //securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        return "login";
    }
}
