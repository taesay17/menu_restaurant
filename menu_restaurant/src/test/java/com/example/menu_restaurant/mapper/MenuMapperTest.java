package com.example.menu_restaurant.mapper;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MenuMapperTest {
    private MenuMapper menuMapper;

    @BeforeEach
    void setUp() {
        menuMapper = new MenuMapperImpl();
    }

    @Test
    void testToMenu() {
        MenuRequest request = new MenuRequest();
        request.setName("Burger");
        request.setPrice(180);
        request.setRating(4);
        request.setCategory("Fast Food");

        Menu menu = menuMapper.toMenu(request);

        assertEquals("Burger", menu.getName());
        assertEquals((180), menu.getPrice());
        assertEquals(4, menu.getRating());
        assertEquals("Fast Food", menu.getCategory());
    }

    @Test
    void testToResponse() {
        Menu menu = new Menu();
        menu.setName("Pizza");
        menu.setPrice(340);
        menu.setCategory("Italian");

        MenuResponse response = menuMapper.toResponse(menu);

        assertEquals("Pizza", response.getName());
        assertEquals((340), response.getPrice());
        assertEquals("Italian", response.getCategory());
    }

    @Test
    void testToMenuList() {
        Menu menu1 = new Menu();
        menu1.setName("Sushi");
        menu1.setPrice(400);
        menu1.setCategory("Japanese");

        Menu menu2 = new Menu();
        menu2.setName("Beshbarmak");
        menu2.setPrice(200);
        menu2.setCategory("Kyrgyz");

        List<MenuResponse> responses = menuMapper.toMenuList(List.of(menu1, menu2));

        assertEquals(2, responses.size());
        assertEquals("Sushi", responses.get(0).getName());
        assertEquals("Kyrgyz", responses.get(1).getCategory());
    }

    @Test
    void testToUpdateMenu() {
        MenuRequest request = new MenuRequest();
        request.setName("Updated Pizza");
        request.setPrice(450);
        request.setRating(4);
        request.setCategory("Italian");

        Menu menu = new Menu();
        menu.setName("Old Pizza");
        menu.setPrice(340);
        menu.setRating(3);
        menu.setCategory("Italian");

        menuMapper.toUpdateMenu(request, menu);

        assertEquals("Updated Pizza", menu.getName());
        assertEquals((450), menu.getPrice());
        assertEquals(4, menu.getRating());
        assertEquals("Italian", menu.getCategory());
    }
}
