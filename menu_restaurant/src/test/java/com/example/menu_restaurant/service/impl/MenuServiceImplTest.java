package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.mapper.MenuMapper;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

    @InjectMocks
    private MenuServiceImpl menuService;

    private Menu menu;
    private MenuRequest menuRequest;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        menu.setId(1L);
        menu.setName("Pizza");
        menu.setPrice(10.99);
        menu.setRating(5);
        menu.setCategory("Fast Food");

        menuRequest = new MenuRequest();
        menuRequest.setName("Pizza");
        menuRequest.setPrice(10.99);
        menuRequest.setRating(5);
        menuRequest.setCategory("Fast Food");
    }

    @Test
    void createMenuList_Success() {
        when(menuMapper.toMenu(menuRequest)).thenReturn(menu);
        when(menuRepository.save(menu)).thenReturn(menu);

        Menu createdMenu = menuService.createMenuList(menuRequest);

        assertNotNull(createdMenu);
        assertEquals("Pizza", createdMenu.getName());
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    void getMenuById_Success() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));

        Menu foundMenu = menuService.getMenuById(1L);
        assertNotNull(foundMenu);
        assertEquals(1L, foundMenu.getId());
    }

    @Test
    void getMenuById_NotFound() {
        when(menuRepository.findById(1L)).thenReturn(Optional.empty());

        Menu foundMenu = menuService.getMenuById(1L);
        assertNull(foundMenu);
    }

    @Test
    void updateMenuById_Success() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(menuMapper.toUpdateMenu(menuRequest, menu)).thenReturn(menu);
        when(menuRepository.save(menu)).thenReturn(menu);

        Menu updatedMenu = menuService.updateMenuById(1L, menuRequest);

        assertNotNull(updatedMenu);
        assertEquals("Pizza", updatedMenu.getName());
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    void deleteMenuById_Success() {
        doNothing().when(menuRepository).deleteById(1L);
        assertDoesNotThrow(() -> menuService.deleteMenuById(1L));
        verify(menuRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAll_Success() {
        when(menuRepository.findAll()).thenReturn(List.of(menu));

        List<Menu> menus = menuService.findAll();
        assertFalse(menus.isEmpty());
        assertEquals(1, menus.size());
    }

    @Test
    void findByRating_Success() {
        when(menuRepository.findByRating(5)).thenReturn(Optional.of(menu));

        Optional<Menu> foundMenu = menuService.findByRating(5);
        assertTrue(foundMenu.isPresent());
        assertEquals(5, foundMenu.get().getRating());
    }

    @Test
    void getMenuByName_Success() {
        when(menuRepository.findByName("Pizza")).thenReturn(Optional.of(menu));

        Optional<Menu> foundMenu = menuService.getMenuByName("Pizza");
        assertTrue(foundMenu.isPresent());
        assertEquals("Pizza", foundMenu.get().getName());
    }

    @Test
    void deleteMenuByName_Success() {
        doNothing().when(menuRepository).deleteMenuByName("Pizza");
        String result = menuService.deleteMenuByName("Pizza");
        assertEquals("Menu Deleted!", result);
        verify(menuRepository, times(1)).deleteMenuByName("Pizza");
    }
}
