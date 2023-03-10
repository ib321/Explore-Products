package com.hcl.explore.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.explore.products.model.Product;

public interface ExploreProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByUserName(String user);
}
