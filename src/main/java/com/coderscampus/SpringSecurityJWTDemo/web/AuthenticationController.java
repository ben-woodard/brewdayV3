package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.response.AuthResponse;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;
import com.coderscampus.SpringSecurityJWTDemo.exceptions.NotFoundException;
import com.coderscampus.SpringSecurityJWTDemo.util.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

//    @PostMapping("/signin")
//    public ResponseEntity<Object> signin(@RequestBody SignInRequest request, HttpServletResponse response) {
//        Optional<User> existingUser = userService.findUserByEmail(request.getEmail());
//        if (existingUser.isPresent()) {
//            User user = existingUser.get();
//
//            try {
//                // Authenticate the user
//                authenticationService.signin(request);
//
//                // Generate tokens
//                String accessToken = jwtService.generateToken(user);
//                RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
//
//                // Set cookies
//                Cookie accessTokenCookie = CookieUtils.createAccessTokenCookie(accessToken);
//                Cookie refreshTokenCookie = CookieUtils.createRefreshTokenCookie(refreshToken.getToken());
//
//                response.addCookie(accessTokenCookie);
//                response.addCookie(refreshTokenCookie);
//
//                // Create response body
//                AuthResponse authResponse = new AuthResponse(user, accessToken, refreshToken.getToken());
//
//                return ResponseEntity.ok(authResponse);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//        }
//    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SignInRequest request, @RequestBody User user) {
        User existingUser = userService.findUserByEmail(user.getEmail()).orElse(null);
        if(user == null) {
            throw new NotFoundException("There was no user found with this username");
        }
        String accessToken = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signin(request);
        AuthResponse authResponse = new AuthResponse(existingUser, jwtAuthenticationResponse);
        return ResponseEntity.ok(authResponse);
    }




//      @PostMapping("/signin") public String authenticateLogin( @RequestBody SignInRequest request) {
//      Optional<User> existingUser = userService.findUserByEmail(request.getEmail());
//      User loggedUser = userService.loadUserByUsername(user.getUsername());
//      String accessToken = jwtService.generateToken(user);
//
//      return ResponseEntity.ok(authenticationService.signin(request)); }


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