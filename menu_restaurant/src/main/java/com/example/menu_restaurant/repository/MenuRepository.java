package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByName(String name);
    Optional<Menu> findByRating(int rating);
    @Transactional
    void deleteMenuByName(String name);
}
