package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import com.example.menu_restaurant.mapper.MenuMapper;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.repository.MenuRepository;
import com.example.menu_restaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public Menu createMenuList(MenuRequest request) {
        return menuRepository.save(menuMapper.toMenu(request));
    }
    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }
    @Override
    public Menu updateMenuById(Long id, MenuRequest request) {
        Menu menu = menuRepository.findById(id).orElse(null);
        menu = menuMapper.toUpdateMenu(request, menu);
        return menuRepository.save(menu);
    }
    @Override
    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();

    }

    @Override
    public List<Menu> findByRating(int rating) {
        return menuRepository.findByRating(rating);
    }

    @Override
    public List<MenuResponse> menuList() {
        return menuMapper.toMenuList(menuRepository.findAll());
    }
    public Optional<Menu> getMenuByName(String name) {
        return menuRepository.findByName(name);
    }
    public String deleteMenuByName(String name) {
        menuRepository.deleteMenuByName(name);
        return "Menu Deleted!";
    }

}
