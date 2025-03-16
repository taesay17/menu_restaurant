package com.example.menu_restaurant.repositoryTest;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        menu.setName("Pizza");
        menu.setPrice(12.99);
        menu.setRating(5);
        menu.setCategory("Fast Food");

        menuRepository.save(menu);
    }

    @Test
    public void testFindByName() {
        Optional<Menu> foundMenu = menuRepository.findByName("Pizza");

        assertThat(foundMenu).isPresent();
        assertThat(foundMenu.get().getName()).isEqualTo("Pizza");
        assertThat(foundMenu.get().getPrice()).isEqualTo(12.99);
        assertThat(foundMenu.get().getRating()).isEqualTo(5);
        assertThat(foundMenu.get().getCategory()).isEqualTo("Fast Food");
    }
}
