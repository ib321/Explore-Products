package com.hcl.explore.products.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.explore.products.model.Product;
import com.hcl.explore.products.service.IExploreProductService;

@Controller
public class ProductController {

	@Autowired
	private IExploreProductService expProductService;

	@RequestMapping(value = "/list-products", method = RequestMethod.GET)
	public String showProducts(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("products", expProductService.getProductsByUser(name));
		return "list-products";
	}

	@RequestMapping(value = "/search-products", method = RequestMethod.GET)
	public String searchProducts(@RequestParam String search, ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("products", expProductService.searchProducts(name, search));
		return "list-products";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadProducts(ModelMap model) {
		return "fileupload";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String showAddProductPage(ModelMap model) {
		model.addAttribute("product", new Product());
		return "product";
	}

	@RequestMapping(value = "/delete-product", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam long id) {
		expProductService.deleteProduct(id);
		return "redirect:/list-products";
	}

	@RequestMapping(value = "/update-product", method = RequestMethod.GET)
	public String showUpdateProductPage(@RequestParam long id, ModelMap model) {
		Product product = expProductService.getProductById(id).get();
		model.put("product", product);
		return "product";
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(ModelMap model, @Valid Product product, BindingResult result) {

		if (result.hasErrors()) {
			return "product";
		}

		product.setUserName(getLoggedInUserName(model));
		expProductService.saveProduct(product);
		return "redirect:/list-products";
	}

	@RequestMapping(value = "/update-product", method = RequestMethod.POST)
	public String updateProduct(ModelMap model, @Valid Product product, BindingResult result) {

		if (result.hasErrors()) {
			return "product";
		}

		product.setUserName(getLoggedInUserName(model));
		expProductService.updateProduct(product);
		return "redirect:/list-products";
	}

}
