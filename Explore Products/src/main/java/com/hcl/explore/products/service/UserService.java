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

import com.hcl.explore.products.model.User;

/**
 * UserService Interface.
 * 
 * @author Indian Bittu
 *
 */
public interface UserService {
	
	/**
	 * Saves a User object to the data store.
	 * 
	 * @param user The User object to save.
	 */
    void save(User user);
    
    /**
     * Retrieves a User object from the data store by username.
     *
     * @param username The username to search for.
     * @return The User object associated with the given username, or null if no
     *         such user is found.
     */
    User findByUsername(String username);
    /**
     * Retrieves a User object from the data store by email.
     *
     * @param email The email to search for.
     * @return The User object associated with the given email, or null if no
     *         such user is found.
     */
    User findByEmail(String email);
}
