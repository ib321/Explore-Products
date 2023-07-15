package com.hcl.explore.products.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.explore.products.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
