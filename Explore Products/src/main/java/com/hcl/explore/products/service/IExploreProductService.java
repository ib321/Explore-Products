package com.hcl.explore.products.service;

import java.util.List;
import java.util.Optional;

import com.hcl.explore.products.model.Product;

public interface IExploreProductService {

	List<Product> getProductsByUser(String user);

	Optional<Product> getProductById(long id);

	void updateProduct(Product product);

	void addProduct(String name, String productName, String productImageSrc, String desc, String productLink,
			boolean isDone);

	void deleteProduct(long id);

	void saveProduct(Product product);

}