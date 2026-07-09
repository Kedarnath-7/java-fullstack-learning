package com.northernarc.codingassessment5.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email) {
        return null;
    }

    public String extractUsername(String token) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }

    public boolean isTokenExpired(String token) {
        return false;
    }
}
