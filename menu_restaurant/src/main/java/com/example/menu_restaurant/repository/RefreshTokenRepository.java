package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.dto.RefreshToken;
import com.example.menu_restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
