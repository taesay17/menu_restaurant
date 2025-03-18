package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        menu.setName("Pizza");
        menu.setPrice(10.99);
        menu.setRating(5);
        menu.setCategory("Fast Food");
        menuRepository.save(menu);
    }



    @Test
    void findByName_Success() {
        Optional<Menu> foundMenu = menuRepository.findByName("Pizza");
        assertTrue(foundMenu.isPresent());
        assertEquals("Pizza", foundMenu.get().getName());
    }

    @Test
    void findByRating_Success() {
        Optional<Menu> foundMenu = menuRepository.findByRating(5);
        assertTrue(foundMenu.isPresent());
        assertEquals(5, foundMenu.get().getRating());
    }

    @Test
    void deleteMenuByName_Success() {
        menuRepository.deleteMenuByName("Pizza");
        Optional<Menu> foundMenu = menuRepository.findByName("Pizza");
        assertFalse(foundMenu.isPresent());
    }
}
