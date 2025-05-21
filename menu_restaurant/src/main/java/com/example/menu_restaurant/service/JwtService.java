package com.example.menu_restaurant.service;

import com.example.menu_restaurant.config.JwtProperties;
import com.example.menu_restaurant.model.RefreshToken;
import com.example.menu_restaurant.repository.RefreshTokenRepository;
import com.example.menu_restaurant.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String username) {
        return jwtUtils.generateAccessToken(username);
    }

    public String generateRefreshToken(String username) {
        String refreshToken = jwtUtils.generateRefreshToken(username);

        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setExpiryDate(Instant.now().plusMillis(3600000));
        token.setUsername(username);
        refreshTokenRepository.save(token);

        return refreshToken;
    }

    public boolean validateRefreshToken(String token) {
        return refreshTokenRepository.existsByToken(token) && jwtUtils.validateToken(token);
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public String refreshAccessToken(String refreshToken) {
        String username = jwtUtils.extractUsername(refreshToken);
        if (validateRefreshToken(refreshToken)) {
            return generateAccessToken(username);
        }
        return null;
    }
}
