package com.coderscampus.SpringSecurityJWTDemo.security;

import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignInRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignUpRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SignInRequest request);
}