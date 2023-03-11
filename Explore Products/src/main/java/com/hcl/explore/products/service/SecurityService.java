package com.hcl.explore.products.service;

public interface SecurityService {
	boolean isAuthenticated();
    void autoLogin(String username, String password);
}
