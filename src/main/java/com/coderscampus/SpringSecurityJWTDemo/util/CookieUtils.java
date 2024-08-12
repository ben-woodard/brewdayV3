package com.coderscampus.SpringSecurityJWTDemo.util;

import jakarta.servlet.http.Cookie;

public class CookieUtils {

	public static Cookie createAccessTokenCookie(String value) {
		Cookie cookie = new Cookie("accessToken", value);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // 1 day
		return cookie;
	}
	
	public static Cookie createRefreshTokenCookie(String value) {
		Cookie cookie = new Cookie("refreshToken", value);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
		return cookie;
	}		
}
