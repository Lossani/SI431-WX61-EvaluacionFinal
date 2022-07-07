package com.gamingworld.app.gamingworld.security.config.jwt;

public class JwtProperties {
    public static final String SECRET = "AOS_Team";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
