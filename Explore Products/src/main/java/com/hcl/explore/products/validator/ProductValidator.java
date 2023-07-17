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
package com.hcl.explore.products.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hcl.explore.products.model.Product;

/**
 * ProductValidator class to validate product ProductValidator is called before
 * saving product to database
 * 
 * @author Indian Bittu
 *
 */
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
		
		if (product.getDescription().length() > 1000) {
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