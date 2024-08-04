package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.domain.Authority;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignUpRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationServiceImpl;
import com.coderscampus.SpringSecurityJWTDemo.security.JwtServiceImpl;
import com.coderscampus.SpringSecurityJWTDemo.service.RefreshTokenService;
import com.coderscampus.SpringSecurityJWTDemo.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    private UserServiceImpl userService;
    private AuthenticationServiceImpl authenticationService;
    private JwtServiceImpl jwtService;
    private RefreshTokenService refreshTokenService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService, AuthenticationServiceImpl authenticationService,
                          JwtServiceImpl jwtService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder) {
        super();
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        createAdminUser();
    }

    public void createAdminUser() {
        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setEmail("admin@email.com");
        adminUser.setPassword(passwordEncoder.encode("adminPassword"));
        Authority adminAuth = new Authority("ROLE_ADMIN", adminUser);
        adminUser.setAuthorities(Collections.singletonList(adminAuth));
        userService.save(adminUser);

    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

}