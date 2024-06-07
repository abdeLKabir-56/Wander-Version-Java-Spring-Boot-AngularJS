package com.blogWebAapp.blogServer.services;

import com.blogWebAapp.blogServer.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
    Boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> extractClaims, UserDetails userDetails);
}
