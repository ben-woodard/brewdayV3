package com.coderscampus.SpringSecurityJWTDemo.dto.response;

public record JwtAuthenticationResponse(String token, String refreshToken) {

}
