package com.coderscampus.SpringSecurityJWTDemo.dto.response;

public record TokenRefreshResponse(String accessToken, String refreshToken) {

}
