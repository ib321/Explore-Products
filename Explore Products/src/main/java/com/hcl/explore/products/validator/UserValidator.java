package com.hcl.explore.products.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hcl.explore.products.model.User;
import com.hcl.explore.products.service.UserService;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (user.getFullname().length() < 2 || user.getFullname().length() > 64) {
        	errors.rejectValue("fullname", "Size.userForm.fullname");
        }

        if (!user.getFullname().matches("^[A-Za-z][A-Za-z' -]+$")) {
            errors.rejectValue("fullname", "Pattern.userForm.fullname");
        }

        if (user.getUsername().length() < 2 || user.getUsername().length() > 64) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        String regex = "[<>:\"/\\\\|?*\u0000-\u001F]";
        if (user.getUsername().matches(regex) || user.getUsername().endsWith(".")) {
        	errors.rejectValue("username", "Pattern.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
