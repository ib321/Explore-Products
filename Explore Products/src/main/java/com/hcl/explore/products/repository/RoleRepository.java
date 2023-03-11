package com.hcl.explore.products.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.explore.products.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
