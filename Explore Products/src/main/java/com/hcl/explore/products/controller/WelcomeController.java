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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.explore.products.service.IExploreProductService;
import com.hcl.explore.products.service.UserService;

/**
 * 
 * @author Indian Bittu
 *
 */
@Controller
public class WelcomeController {

	@Autowired
	private IExploreProductService expProductService;

	@Autowired
	private UserService userService;
	
	@Value("${url.username.isEncrypted}")
	private boolean isUrlUserNameEnc;

	/**
	 * Handles GET requests to the / and /welcome URLs and displays a welcome
	 * page to the user also puts urlEncodedUserName to model.
	 *
	 * @param model The ModelMap object representing the model.
	 * @return The view name for displaying the welcome page.
	 */
	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		String userName = getLoggedinUserName();
		String fullname = getFullName();
		model.put("fullname", fullname);
		model.put("products", expProductService.getProductsByUser(userName));
		if (isUrlUserNameEnc) {
			BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
			textEncryptor.setPassword("Encrypted@#137");
			userName = textEncryptor.encrypt(userName);
		}
		String urlEncodedUserName = userName;
		try {
			urlEncodedUserName = URLEncoder.encode(userName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.put("urlUserName", urlEncodedUserName);
		return "welcome";
	}

	/**
	 * Handles GET requests to the /search and /searchWelcome URLs and displays
	 * a welcome page with search results to the user.
	 *
	 * @param search The search query entered by the user.
	 * @param model The ModelMap object representing the model.
	 * @return The view name for displaying the welcome page with search results.
	 */
	@RequestMapping(value = { "/search", "/searchWelcome" }, method = RequestMethod.GET)
	public String showWelcomePage(@RequestParam String search,ModelMap model) {
		model.put("name", getLoggedinUserName());
		model.put("products", expProductService.searchProducts(getLoggedinUserName(), search));
		return "welcome";
	}

	/**
	 * Retrieves the user-id of the currently logged-in user.
	 *
	 * @return The user-id of the currently logged-in user, or the string representation
	 *         of the principal object if the principal is not an instance of UserDetails.
	 */
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	/**
	 * Retrieves the Full Name of the currently logged-in user.
	 *
	 * @return The Full Name of the currently logged-in user.
	 */
	private String getFullName() {
		return userService.findByUsername(getLoggedinUserName()).getFullname();
	}

}
