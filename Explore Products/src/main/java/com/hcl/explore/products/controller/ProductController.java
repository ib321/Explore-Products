package com.hcl.explore.products.controller;

import java.io.IOException;
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

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProduct(ModelMap model, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
		productValidator.validate(product, result);
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
		String msg = "Product with <strong>Name: " + productName + "</strong> has been deleted successfully.";
		redirectAttributes.addFlashAttribute("dangermsg", msg);
		return "redirect:/list-products";
	}

	@RequestMapping(value = "/autoFetchProductDetails", method = RequestMethod.GET)
	public String autoFetchProductDetails(@RequestParam String productUrl, Product product, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
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
