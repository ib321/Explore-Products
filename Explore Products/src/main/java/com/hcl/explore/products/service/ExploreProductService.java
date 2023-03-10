package com.hcl.explore.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.explore.products.model.Product;
import com.hcl.explore.products.repository.ExploreProductRepository;

@Service
public class ExploreProductService implements IExploreProductService {

	@Autowired
	private ExploreProductRepository expProductRepository;

	@Override
	public List<Product> getProductsByUser(String user) {
		return expProductRepository.findByUserName(user);
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
	public void addProduct(String name, String productName, String productImageSrc, String desc, String productLink,
			boolean isDone) {
		expProductRepository.save(new Product(name, productName, productImageSrc, desc, productLink, isDone));
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
}