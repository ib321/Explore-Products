package com.hcl.explore.products.service;

import java.util.ArrayList;
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
}