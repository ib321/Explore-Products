package com.hcl.explore.products.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hcl.explore.products.model.Product;
import com.hcl.explore.products.service.IExploreProductService;

@Controller
public class ProductController {

	@Autowired
	private IExploreProductService expProductService;

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

	@RequestMapping(value = "/show-products", method = RequestMethod.GET)
	public String showAllProductPage(@RequestParam String user, ModelMap model) {
		model.put("products", expProductService.getProductsByUser(user));
		return "showproduct";
	}

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

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadPage(ModelMap model) {
		return "fileupload";
	}
	
	
	/*-------------------------------------------------------------
	 * Below Listed Method also have Purpose
	 * Other than returning only JSP page
	 * ------------------------------------------------------------
	 */

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(ModelMap model, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "product";
		}

		final Path path = Paths.get("./src/main/resources/static/images/" + getLoggedInUserName(model) + "/");
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
		final Path path = Paths.get("./src/main/resources/static/images/" + getLoggedInUserName(model) + "/");
		MultipartFile file = product.getProductImageFile();
		product.setProductImageSrc("/images/" + getLoggedInUserName(model) + "/" + expProductService.saveProductImage(path, file));

		product.setUserName(getLoggedInUserName(model));
		expProductService.updateProduct(product);
		redirectAttributes.addFlashAttribute("message", "Product Updated successfully.");
		return "redirect:/list-products";
	}
	
	@RequestMapping(value = "/delete-product", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam long id, RedirectAttributes redirectAttributes, ModelMap model) {
		String productName = expProductService.getProductById(id).get().getProductName();
		String productImageSrc = expProductService.getProductById(id).get().getProductImageSrc();
		expProductService.deleteProduct(id);
		expProductService.deleteProductImage(productImageSrc);
		String msg = "Product with  Name: "+ productName + "  has been deleted successfully.";
		redirectAttributes.addFlashAttribute("dangermsg", msg);
		return "redirect:/list-products";
	}

	@RequestMapping(value = "/getImage", method = RequestMethod.GET)
	public String getImage(@RequestParam String productUrl, Product product, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) throws IOException {
		String productLink=productUrl;
		if (productUrl == null || productUrl.isEmpty())
			return "redirect:/add-product";
		Document doc = Jsoup.connect(productUrl).get();
		String imageUrl;
		if (productUrl.contains("amazon"))
			imageUrl = doc.select("#landingImage").attr("src");
		else if (productUrl.contains("flipkart"))
			imageUrl = doc.select("img._396cs4").attr("src");
		else
			imageUrl = "";
		if (imageUrl.isEmpty())
			imageUrl = "Try Once Again";
		redirectAttributes.addFlashAttribute("imageUrl", imageUrl);
		redirectAttributes.addFlashAttribute("productUrl", productLink);
		return "redirect:/add-product";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadImage(ModelMap model, @RequestParam("file") MultipartFile file) {
		final Path root = Paths.get("./src/main/resources/static/images");
		try {
			Files.createDirectories(root);
			Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/add-product";
	}

}
