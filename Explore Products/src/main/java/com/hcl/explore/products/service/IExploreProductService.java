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

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hcl.explore.products.model.Product;

/**
 * 
 * @author Indian Bittu
 *
 */
public interface IExploreProductService {

	List<Product> getProductsByUser(String user);
	
	List<Product> searchProducts(String user,String search);

	Optional<Product> getProductById(long id);

	void updateProduct(Product product);

	void addProduct(String name, String productName, String productImageSrc, String desc, String productLink, boolean isDone);

	void deleteProduct(long id);

	void saveProduct(Product product);
	
	boolean deleteProductImage(String productImageSrc);
	
	String saveProductImage(Path path, MultipartFile file);

}