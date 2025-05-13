package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.RefreshToken;
import com.example.menu_restaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByToken(String token);
    void deleteByToken(String token);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);

}
