package com.coderscampus.SpringSecurityJWTDemo.web;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignUpRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

}