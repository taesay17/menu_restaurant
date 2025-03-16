package com.example.menu_restaurant;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import com.example.menu_restaurant.repository.MenuRepository;
import com.example.menu_restaurant.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MenuServiceIntegrationTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    private Long testMenuId;

    @BeforeEach
    void setUp() {
        // Insert test data into the in-memory H2 database
        Menu menu = new Menu();
        menu.setName("Burger");
        menu.setPrice(180);
        menu.setRating(4);
        menu.setCategory("Fast Food");
        menu = menuRepository.save(menu);

        testMenuId = menu.getId();
    }

    @Test
    void testCreateMenu() {
        MenuRequest request = new MenuRequest();
        request.setName("Pizza");
        request.setPrice(340);
        request.setRating(4);
        request.setCategory("Italian");

        Menu createdMenu = menuService.createMenuList(request);

        assertNotNull(createdMenu);
        assertNotNull(createdMenu.getId());
        assertEquals("Pizza", createdMenu.getName());
        assertEquals(BigDecimal.valueOf(12.99), createdMenu.getPrice());
    }

    @Test
    void testGetMenuById() {
        Menu menu = menuService.getMenuById(testMenuId);

        assertNotNull(menu);
        assertEquals("Burger", menu.getName());
        assertEquals((180), menu.getPrice());
    }

    @Test
    void testUpdateMenuById() {
        MenuRequest request = new MenuRequest();
        request.setName("Updated Burger");
        request.setPrice(210);
        request.setRating(3);
        request.setCategory("Fast Food");

        Menu updatedMenu = menuService.updateMenuById(testMenuId, request);

        assertNotNull(updatedMenu);
        assertEquals("Updated Burger", updatedMenu.getName());
        assertEquals((210), updatedMenu.getPrice());
    }

    @Test
    void testDeleteMenuById() {
        menuService.deleteMenuById(testMenuId);

        Optional<Menu> deletedMenu = menuRepository.findById(testMenuId);
        assertFalse(deletedMenu.isPresent());
    }

    @Test
    void testFindAll() {
        List<Menu> menus = menuService.findAll();

        assertFalse(menus.isEmpty());
        assertEquals(1, menus.size());
    }

    @Test
    void testFindByRating() {
        Optional<Menu> menu = menuService.findByRating(4);

        assertTrue(menu.isPresent());
        assertEquals("Burger", menu.get().getName());
    }

    @Test
    void testMenuList() {
        List<MenuResponse> menuResponses = menuService.menuList();

        assertFalse(menuResponses.isEmpty());
        assertEquals(1, menuResponses.size());
        assertEquals("Burger", menuResponses.get(0).getName());
    }

    @Test
    void testGetMenuByName() {
        Optional<Menu> menu = menuService.getMenuByName("Burger");

        assertTrue(menu.isPresent());
        assertEquals("Burger", menu.get().getName());
    }

    @Test
    void testDeleteMenuByName() {
        String response = menuService.deleteMenuByName("Burger");

        assertEquals("Menu Deleted!", response);
        Optional<Menu> menu = menuService.getMenuByName("Burger");
        assertFalse(menu.isPresent());
    }
}
