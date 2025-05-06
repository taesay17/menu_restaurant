package com.example.menu_restaurant.service;

import com.example.menu_restaurant.model.RefreshToken;
import com.example.menu_restaurant.model.User;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    Optional<RefreshToken> findByToken(String token);

    boolean isExpired(RefreshToken refreshToken);

    void deleteByUser(User user);
}
