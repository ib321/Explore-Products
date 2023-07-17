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
package com.hcl.explore.products.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfiguration Class to handle security related to login logout.
 * 
 * @author Indian Bittu
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/**
	 * An instance of the UserDetailsService interface used to load user details
	 * from the data store.
	 * <br>
	 * <strong>userDetailsServiceImpl implements UserDetailsService.</strong>
	 */
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Creates and returns a new instance of the BCryptPasswordEncoder class used
	 * to encode and verify passwords using the BCrypt hashing algorithm.
	 *
	 * @return A new instance of the BCryptPasswordEncoder class.
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures HTTP security for the application by specifying which requests
	 * should be permitted without authentication and which requests should be
	 * authenticated. Also configures form-based login and logout.
	 *
	 * @param http The HttpSecurity object used to configure HTTP security.
	 * @throws Exception If an error occurs while configuring HTTP security.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/images/**", "/js/**", "/registration", "/show-products", "/commonimage/**", "/webjars/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

	/**
	 * Creates and returns an instance of the AuthenticationManager interface used
	 * to authenticate users and manage authentication-related operations.
	 *
	 * @return An instance of the AuthenticationManager interface.
	 * @throws Exception If an error occurs while creating the AuthenticationManager.
	 */
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	/**	<strong>Important for security Configuration Changes !</strong>
	 * <br>
	 * Configures global authentication-related settings by specifying which
	 * UserDetailsService and PasswordEncoder implementations should be used.
	 *
	 * @param auth The AuthenticationManagerBuilder object used to configure global
	 *             authentication-related settings.
	 * @throws Exception If an error occurs while configuring global authentication-related settings.
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

  //Code related to in memory authentication Need to be removed
  /*
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
        		.withUser("admin").password("admin")
                .roles("USER", "ADMIN");
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers("/login", "/*products*\**").access("hasRole('USER')").and().formLogin();

        http.csrf().ignoringAntMatchers("/show-products?user=admin");
        //http.csrf().disable();
        http.headers().frameOptions().disable();
    }
	*/
}
