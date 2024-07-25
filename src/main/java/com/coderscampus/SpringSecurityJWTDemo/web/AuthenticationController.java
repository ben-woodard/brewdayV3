package com.coderscampus.SpringSecurityJWTDemo.web;

import com.coderscampus.SpringSecurityJWTDemo.dto.response.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.coderscampus.SpringSecurityJWTDemo.dto.request.RefreshTokenRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.request.SignInRequest;
import com.coderscampus.SpringSecurityJWTDemo.dto.response.TokenRefreshResponse;
import com.coderscampus.SpringSecurityJWTDemo.domain.RefreshToken;
import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import com.coderscampus.SpringSecurityJWTDemo.security.AuthenticationServiceImpl;
import com.coderscampus.SpringSecurityJWTDemo.security.JwtService;
import com.coderscampus.SpringSecurityJWTDemo.service.RefreshTokenService;
import com.coderscampus.SpringSecurityJWTDemo.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/v1/auth") 
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
    public String signin (@RequestBody SignInRequest request) {
        System.out.println(request);
        System.out.println(request);
        return "Fuck You";
    }

//    @PostMapping("/signin")
//    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request, @RequestBody User user) {
//        System.out.println(user);
//    	Optional<User> existingUser = userService.findUserByEmail(user.getEmail());
//    	String accessToken = jwtService.generateToken(user);
//        return ResponseEntity.ok(authenticationService.signin(request));
//    }


//	 This code is from Trevor's original implementation which might be helpful for those who are not using server rendering templates

//    @PostMapping("/signin")
//    public ResponseEntity<JwtAuthenticationResponse> authenticateLogin(@RequestBody SignInRequest request) {
//        // Validate input
//        if (request.email() == null || request.password() == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        try {
//            // Create an authentication token using the email and password from the request
//            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
//
//            // Authenticate the user using AuthenticationManager
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            // Generate JWT token after successful authentication
//            String accessToken = jwtService.generateToken((User) authentication.getPrincipal());
//
//            // Return JWT token in the response
//            return ResponseEntity.ok(new JwtAuthenticationResponse(String.valueOf(new HashMap<>()), accessToken));
//        } catch (BadCredentialsException e) {
//            // Handle invalid credentials
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        } catch (Exception e) {
//            // Handle other exceptions
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
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