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
package com.hcl.explore.products.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.explore.products.model.User;
import com.hcl.explore.products.repository.RoleRepository;
import com.hcl.explore.products.repository.UserRepository;

/**
 * UserServiceImpl class implements UserService.
 * 
 * @author Indian Bittu
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     *{@inheritDoc}
     *
     * This method encodes the user's password using the BCryptPasswordEncoder,
     * sets the user's roles to all available roles, and saves the user to the
     * data store using the userRepository.
     *
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     * 
     * This method uses the userRepository to find a User object associated with
     * the given username by calling the findByUsername method.
     *
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     * 
     * This method uses the userRepository to find a User object associated with
     * the given email by calling the findByEmail method.
     *
     */
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
