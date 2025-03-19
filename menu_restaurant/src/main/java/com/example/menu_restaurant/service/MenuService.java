package com.example.menu_restaurant.service;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import com.example.menu_restaurant.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    Menu createMenuList(MenuRequest menuRequest);
    Menu getMenuById(Long id);
    Menu updateMenuById(Long id, MenuRequest request);
    void deleteMenuById(Long id);
    List<Menu> findAll();
    List<Menu> findByRating(int rating);
    List<MenuResponse> menuList();
    Optional<Menu> getMenuByName(String name);
    String deleteMenuByName(String name);
}
