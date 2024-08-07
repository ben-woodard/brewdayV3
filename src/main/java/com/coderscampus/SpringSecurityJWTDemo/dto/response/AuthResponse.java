package com.coderscampus.SpringSecurityJWTDemo.dto.response;

import com.coderscampus.SpringSecurityJWTDemo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {

    private User user;
    private String accessToken;
    private String refreshToken;

}
