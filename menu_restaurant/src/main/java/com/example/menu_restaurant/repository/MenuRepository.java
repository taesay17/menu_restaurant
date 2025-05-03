package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);

    List<Menu> findByRating(int rating);

    boolean existsByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.name = :name")
    void deleteMenuByName(@Param("name") String name);
}

