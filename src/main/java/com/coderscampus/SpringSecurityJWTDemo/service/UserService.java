package com.coderscampus.SpringSecurityJWTDemo.service;

import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<User> findAll();

    User findById(Integer userId);

    void save(User adminUser);
}