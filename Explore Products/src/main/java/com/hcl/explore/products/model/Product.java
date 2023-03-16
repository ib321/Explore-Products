package com.hcl.explore.products.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userName;
	private String productName;
	private String productImageSrc;
	@Size(min = 10, message = "Enter at least 10 Characters...")
	private String description;
	private String productLink;

	public Product() {
		super();
	}

	public Product(String userName, String productName, String productImageSrc, String desc, String productLink,
			boolean isDone) {
		super();
		this.userName = userName;
		this.productName = productName;
		this.productImageSrc = productImageSrc;
		this.description = desc;
		this.productLink = productLink;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageSrc() {
		return productImageSrc;
	}

	public void setProductImageSrc(String productImageSrc) {
		this.productImageSrc = productImageSrc;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

}