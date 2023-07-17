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


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hcl.explore.products.model.User;
import com.hcl.explore.products.service.UserService;

/**
 * 
 * @author Indian Bittu
 *
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (user.getFullname().length() < 2 || user.getFullname().length() > 64) {
        	errors.rejectValue("fullname", "Size.userForm.fullname");
        }

        /**
         * Validates the full name of a user.
         *
         * The regular expression used for validation allows full names that:
         * - Start with an alphabetic character
         * - Followed by one or more alphabetic characters, spaces, hyphens, or apostrophes
         */
        if (!user.getFullname().matches("^[A-Za-z][A-Za-z' -.]+$")) {
            errors.rejectValue("fullname", "Pattern.userForm.fullname");
        }

        if (user.getUsername().length() < 4 || user.getUsername().length() > 20) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        /**
         * Validates the username of a user.
         *
         * The regular expression used for validation allows usernames that:
         * - Start with an alphabetic character
         * - Followed by zero or more alphanumeric characters
         */
        String regex = "^[A-Za-z][A-Za-z0-9]*$";
        if (!user.getUsername().matches(regex)) {
        	errors.rejectValue("username", "Pattern.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (user.getEmail().length() < 2 || user.getEmail().length() > 64) {
        	errors.rejectValue("email", "Size.userForm.email");
        }

        /**
         * Validates the email address of a user.
         *
         * The regular expression used for validation allows email addresses that:
         * - Have a maximum length of 64 characters before the '@' symbol
         * - Start with one or more alphanumeric characters, underscores, or hyphens
         * - Optionally contain one or more '.' symbols followed by one or more alphanumeric characters, underscores, or hyphens
         * - Contain an '@' symbol followed by one or more alphanumeric characters or hyphens (but not starting with a hyphen)
         * - Contain one or more '.' symbols followed by one or more alphanumeric characters or hyphens
         * - End with a '.' symbol followed by two or more alphabetic characters
         */
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Pattern.matches(emailRegex, user.getEmail())) {
        	errors.rejectValue("email", "Pattern.userForm.email");
        }
        
        if (userService.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Duplicate.userForm.email");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 108) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
