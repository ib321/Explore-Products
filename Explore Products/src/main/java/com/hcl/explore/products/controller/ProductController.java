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

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hcl.explore.products.model.Product;
import com.hcl.explore.products.service.IExploreProductService;
import com.hcl.explore.products.validator.ProductValidator;

/**
 * 
 * @author Indian Bittu
 *
 */
@Controller
public class ProductController {

	@Autowired
	private IExploreProductService expProductService;
	
	@Autowired
    private ProductValidator productValidator;

	@Value("${url.username.isEncrypted}")
	private boolean isUrlUserNameEnc;
	
	private static final String  STATIC_PARTIAL_IMAGE_PATH = "./src/main/resources/static/images/";

	/**
	 * Retrieves the user-id of the currently logged-in user.
	 *
	 * @param model The ModelMap object representing the model.
	 * @return The user-id of the currently logged-in user, or the string representation
	 *         of the principal object if the principal is not an instance of UserDetails.
	 */
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	/*-------------------------------------------------------
	 * Below Listed Methods Main Function
	 * is to Return JSP Page
	 * ------------------------------------------------------
	 */

	/**
	 * Handles requests to the /list-products URL and displays a list of products
	 * associated with the currently logged-in user.
	 *
	 * @param model The ModelMap object representing the model.
	 * @return The view name "list-products" for displaying the list of products.
	 */
	@RequestMapping(value = "/list-products", method = RequestMethod.GET)
	public String showProducts(ModelMap model) {
		String userName = getLoggedInUserName(model);
		model.put("products", expProductService.getProductsByUser(userName));
		return "list-products";
	}

	@RequestMapping(value = "/search-products", method = RequestMethod.GET)
	public String searchProducts(@RequestParam String search, ModelMap model) {
		String userName = getLoggedInUserName(model);
		model.put("products", expProductService.searchProducts(userName, search));
		return "list-products";
	}

	/**
	 * Handles requests to the /show-products URL and displays a list of products
	 * associated with the user specified by the refShareId parameter.
	 *
	 * @param refShareId The encrypted or unencrypted username of the user.
	 * @param model The ModelMap object representing the model.
	 * @return The view name "showproduct" for displaying the list of products.
	 */
	@RequestMapping(value = "/show-products", method = RequestMethod.GET)
	public String showAllProductPage(@RequestParam String refShareId, ModelMap model) {
		String user=refShareId;
		try {
			if (isUrlUserNameEnc) {
				BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
				textEncryptor.setPassword("Encrypted@#137");
				user = textEncryptor.decrypt(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put("dangermsg", "An error occurred: " + e.getMessage());
		}
		model.put("products", expProductService.getProductsByUser(user));
		return "showproduct";
	}

	/**
	 * Handles GET requests to the /add-product URL and displays a page for adding
	 * a new product provide a fresh new Product object.
	 *
	 * @param model The ModelMap object representing the model.
	 * @return The view name "product" for displaying the page for adding a new product.
	 */
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String showAddProductPage(ModelMap model) {
		model.addAttribute("product", new Product());
		return "product";
	}
	
	@RequestMapping(value = "/update-product", method = RequestMethod.GET)
	public String showUpdateProductPage(@RequestParam long id, ModelMap model) {
		Product product = expProductService.getProductById(id).get();
		model.put("product", product);
		return "product";
	}
	
	
	/*-------------------------------------------------------------
	 * Below Listed Method also have Purpose
	 * Other than returning only JSP page
	 * ------------------------------------------------------------
	 */

	/**
	 * Handles POST requests to the /add-product URL and adds a new product to
	 * the App also save the product image.
	 *
	 * @param model The ModelMap object representing the model.
	 * @param product The Product object representing the product to add.
	 * @param result The BindingResult object representing binding results.
	 * @param redirectAttributes The RedirectAttributes object used to add flash attributes.
	 * @return A redirect to view either the /product or /list-products.
	 */
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(ModelMap model, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
		productValidator.validate(product, result);
		if (result.hasErrors()) {
			return "product";
		}

		final Path path = Paths.get(STATIC_PARTIAL_IMAGE_PATH + getLoggedInUserName(model) + "/");
		MultipartFile file = product.getProductImageFile();
		product.setProductImageSrc("/images/" + getLoggedInUserName(model) + "/" + expProductService.saveProductImage(path, file));

		product.setUserName(getLoggedInUserName(model));
		expProductService.saveProduct(product);
		redirectAttributes.addFlashAttribute("message", "Product added successfully.");
		return "redirect:/list-products";
	}

	@RequestMapping(value = "/update-product", method = RequestMethod.POST)
	public String updateProduct(ModelMap model, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "product";
		}

		String productImageSrc = product.getProductImageSrc();
		expProductService.deleteProductImage(productImageSrc);
		final Path path = Paths.get(STATIC_PARTIAL_IMAGE_PATH + getLoggedInUserName(model) + "/");
		MultipartFile file = product.getProductImageFile();
		product.setProductImageSrc("/images/" + getLoggedInUserName(model) + "/" + expProductService.saveProductImage(path, file));

		product.setUserName(getLoggedInUserName(model));
		expProductService.updateProduct(product);
		redirectAttributes.addFlashAttribute("message", "Product Updated successfully.");
		return "redirect:/list-products";
	}

	/**
	 * Handles GET requests to the /delete-product URL and deletes a product with
	 * a given ID also deletes the product image.
	 *
	 * @param id The ID of the product to delete.
	 * @param redirectAttributes The RedirectAttributes object used to add flash attributes.
	 * @param model The ModelMap object representing the model.
	 * @return A redirect to the /list-products URL.
	 */
	@RequestMapping(value = "/delete-product", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam long id, RedirectAttributes redirectAttributes, ModelMap model) {
		String productName = expProductService.getProductById(id).get().getProductName();
		String productImageSrc = expProductService.getProductById(id).get().getProductImageSrc();
		expProductService.deleteProduct(id);
		expProductService.deleteProductImage(productImageSrc);
		String msg = "Product with <strong>Name: " + productName + "</strong> has been deleted successfully.";
		redirectAttributes.addFlashAttribute("dangermsg", msg);
		return "redirect:/list-products";
	}

	/**
	 * Handles GET requests to the /autoFetchProductDetails URL and automatically fetches
	 * product details like name,description,imageLink from a given product URL.
	 *
	 * @param productUrl The URL of the product to fetch details from.
	 * @param product The Product object representing the product.
	 * @param result The BindingResult object representing binding results.
	 * @param model The ModelMap object representing the model.
	 * @param redirectAttributes The RedirectAttributes object used to add flash attributes.
	 * @param request The HttpServletRequest object representing the current request.
	 * @return it redirect to the same URL from where request has came for autoFetchProductDetails.
	 * 
	 */
	@RequestMapping(value = "/autoFetchProductDetails", method = RequestMethod.GET)
	public String autoFetchProductDetails(@RequestParam String productUrl, Product product, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String referer = request.getHeader("referer");
	    String redirectUrl="/add-product";
	    if (referer != null) {
	        if (referer.contains("/add-product")) {
	            redirectUrl = "/add-product";
	        } else if (referer.contains("/update-product")) {
	        	 try {
	                 URI refererUri = new URI(referer);
	                 redirectUrl = refererUri.getPath() + "?" + refererUri.getQuery();
	             } catch (URISyntaxException e) {
	                 e.printStackTrace();
	             }
	        }
	    }
		String productLink = productUrl;
		if (productUrl == null || productUrl.isEmpty())
			return "redirect:/add-product";
		try {
			Document doc = Jsoup.connect(productUrl)
					  .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
					  .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
					  .header("Referer", "https://www.amazon.com/")
					  .get();
			String imageUrl;
			String productName;
			String productDescription;

			if (productUrl.contains("amazon")) {
			    imageUrl = doc.select("#landingImage").attr("src");
			    productName = doc.select("#productTitle").text();
			    productDescription = doc.select("#feature-bullets").text();
			} else if (productUrl.contains("flipkart")) {
			    imageUrl = doc.select("img._396cs4").attr("src");
			    productName = doc.select("span.B_NuCI").text();
			    productDescription = doc.select("div._1mXcCf.RmoJUa").text();
			} else {
			    imageUrl = "";
			    productName = "";
			    productDescription = "";
			}

			if (imageUrl.isEmpty())
			    imageUrl = "Did not worked!";
			redirectAttributes.addFlashAttribute("imageUrl", imageUrl);
			redirectAttributes.addFlashAttribute("productUrl", productLink);
			redirectAttributes.addFlashAttribute("productNameFill", productName);
			redirectAttributes.addFlashAttribute("productDescFill", productDescription);
			model.put("product", product);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("dangermsg", "An error occurred while connecting: " + e.getMessage());
		}
		return "redirect:"+ redirectUrl;
	}

}
