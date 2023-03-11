package com.hcl.explore.products.service;

import com.hcl.explore.products.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
