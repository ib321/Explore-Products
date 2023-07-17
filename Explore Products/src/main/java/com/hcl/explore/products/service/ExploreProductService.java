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
package com.hcl.explore.products.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.explore.products.model.Product;
import com.hcl.explore.products.repository.ExploreProductRepository;

/**
 * Product Service class implements IExploreProductService.
 * 
 * @author Indian Bittu
 *
 */
@Service
public class ExploreProductService implements IExploreProductService {

	@Autowired
	private ExploreProductRepository expProductRepository;

	@Override
	public List<Product> getProductsByUser(String user) {
		List<Product> productList = expProductRepository.findByUserName(user);
		Collections.reverse(productList);
		return productList;
	}

	@Override
	public List<Product> searchProducts(String user, String search) {
		List<Product> product = new ArrayList<Product>();
		List<Product> products = expProductRepository.findByUserName(user);
		for (Product p : products) {
			if (p.getProductName().toLowerCase().contains(search.toLowerCase())) {
				product.add(p);
			}
		}
		return product;
	}

	@Override
	public Optional<Product> getProductById(long id) {
		return expProductRepository.findById(id);
	}

	@Override
	public void updateProduct(Product product) {
		expProductRepository.save(product);
	}

	@Override
	public void addProduct(String userName, String productName, String productImageSrc, String desc, String productLink,
			boolean isDone) {
		expProductRepository.save(new Product(userName, productName, productImageSrc, desc, productLink, isDone));
	}

	@Override
	public void deleteProduct(long id) {
		Optional<Product> product = expProductRepository.findById(id);
		if (product.isPresent()) {
			expProductRepository.delete(product.get());
		}
	}

	@Override
	public void saveProduct(Product product) {
		expProductRepository.save(product);
	}

	@Override
	public boolean deleteProductImage(String productImageSrc) {
		if (productImageSrc != null)
			productImageSrc = productImageSrc.replaceAll("[^a-zA-Z0-9./_-]", "");
		final Path path = Paths.get("./src/main/resources/static" + productImageSrc);
		try {
			boolean result = Files.deleteIfExists(path);
			if (result) {
				System.out.println("File deleted successfully: " + path);
				return true;
			} else {
				System.out.println("File does not exist: " + path);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Failed to delete file: " + path + e);
			return false;
		}
	}

	@Override
	public String saveProductImage(Path path, MultipartFile file) {
		
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		fileName = fileName.replaceAll("[^a-zA-Z0-9./_-]", "");
		try {
			Files.createDirectories(path);
			Files.copy(file.getInputStream(), path.resolve(fileName));
			//Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}