package com.example.viacademy.utils;

import com.example.viacademy.types.JWTType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    @Value("${jwt.access-token-secret-key}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${jwt.access-token-expiration-in-ms}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;

    public String generateAccessToken(String email, Map<String, Object> payload, JWTType type) {
        String secretKey = this.getTokenSecretKey(type);
        Date expirationTime = this.getExpirationTime(type);

        return Jwts.builder()
                .subject(email)
                .claims(payload)
                .expiration(expirationTime)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String getTokenSecretKey(JWTType type) {
        switch (type) {
            case ACCESS_TOKEN:
                return ACCESS_TOKEN_SECRET_KEY;
            default:
                throw new RuntimeException("JWT type not supported");
        }
    }

    private Date getExpirationTime(JWTType type) {
        switch (type) {
            case ACCESS_TOKEN:
                return new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);
            default:
                throw new RuntimeException("JWT type not supported");
        }
    }

    public String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return null;
        }
        return bearerToken.substring(7);

    }

    public boolean isJWTValid(String jwt, JWTType type) {
        String secretKey = this.getTokenSecretKey(type);

        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getUsernameFromJWT(String jwt, JWTType type) {
        String secretKey = this.getTokenSecretKey(type);

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(jwt).getPayload().getSubject();

    }
}