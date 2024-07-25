package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.coderscampus.SpringSecurityJWTDemo.dto.request.RefreshTokenRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignInRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.TokenRefreshResponse;
import com.coderscampus.SpringSecurityJWTDemo.domain.RefreshToken;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationServiceImpl;
import com.coderscampus.SpringSecurityJWTDemo.security.JwtService;
import com.coderscampus.SpringSecurityJWTDemo.service.RefreshTokenService;
import com.coderscampus.SpringSecurityJWTDemo.service.impl.UserServiceImpl;

import java.util.Optional;

//@RestController
@RequestMapping("/api/v1/auth")
@Controller
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/signin")
    public String getLogin(@ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
//    	Optional<User> existingUser = userService.findUserByEmail(request.email());
//    	String accessToken = jwtService.generateToken(user);
        return ResponseEntity.ok(authenticationService.signin(request));
    }


//	 This code is from Trevor's original implementation which might be helpful for those who are not using server rendering templates

//      @PostMapping("/signin") public ResponseEntity<JwtAuthenticationResponse> authenticateLogin( SignInRequest request) {
//      Optional<User> existingUser = userService.findUserByEmail(user.getEmail());
//      User loggedUser = userService.loadUserByUsername(user.getUsername());
//      String accessToken = jwtService.generateToken(user);
//      return ResponseEntity.ok(authenticationService.signin(request));
//    }




    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.refreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new IllegalStateException(
                        "Refresh token " + requestRefreshToken + " is not in database!"));
    }
}