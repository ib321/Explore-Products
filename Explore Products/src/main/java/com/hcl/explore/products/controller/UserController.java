/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.hcl.explore.products.security.SecurityService;
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

    /**
     * Handles GET requests to the /registration URL and displays a registration
     * page to the user.
     *
     * @param model The Model object representing the model.
     * @return A redirect to the root URL if the user is authenticated, or the
     *         view name for displaying the registration page otherwise.
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * Handles POST requests to the /registration URL and processes user
     * registration by validating and saving a new User object.
     *
     * @param userForm The User object representing the user to register.
     * @param result The BindingResult object representing binding results.
     * @param redirectAttributes The RedirectAttributes object used to add flash attributes.
     * @return A redirect to either the /registration or /login URL.
     */
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

    /**
     * Handles GET requests to the /login URL and displays a login page
     * to the user.
     *
     * @param model The Model object representing the model.
     * @param error An error message indicating that an authentication error occurred.
     * @param logout A logout message indicating that logout was successful.
     * @return A redirect to either root URL if authenticated or view name for displaying login page otherwise.
     */
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
