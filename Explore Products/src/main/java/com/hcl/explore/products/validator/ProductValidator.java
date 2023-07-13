package com.hcl.explore.products.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hcl.explore.products.model.Product;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Product product = (Product) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productLink", "NotEmpty");

		if (product.getProductName().length() > 400) {
			errors.rejectValue("productName", "size.max.productName");
		}

		if (product.getDescription().length() < 10) {
			errors.rejectValue("description", "size.min.description");
		}
		
		if (product.getDescription().length() > 600) {
			errors.rejectValue("description", "size.max.description");
		}

		if (product.getProductLink().length() > 1000) {
			errors.rejectValue("productLink", "size.max.productLink");
		}

		if (product.getProductImageFile().getSize() > 1048576) {
			errors.rejectValue("productImageFile", "size.max.productImageFile");
		}
	}
}