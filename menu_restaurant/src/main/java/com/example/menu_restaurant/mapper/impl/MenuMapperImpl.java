package com.example.menu_restaurant.mapper.impl;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import com.example.menu_restaurant.mapper.MenuMapper;
import com.example.menu_restaurant.model.Menu;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuMapperImpl implements MenuMapper {
    @Override
    public Menu toMenu(MenuRequest request) {
        Menu menu = new Menu();
        menu.setName(request.getName());
        menu.setPrice(request.getPrice());
        menu.setRating(request.getRating());
        return menu;
    }

    @Override
    public MenuResponse toResponse(Menu menu) {
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setName(menu.getName());
        menuResponse.setPrice(menu.getPrice());
        return menuResponse;
    }

    @Override
    public List<MenuResponse> toMenuList(List<Menu> menuList) {
        List<MenuResponse> responses = new ArrayList<>();
        for (Menu menu : menuList) {
            responses.add(toResponse(menu));
        }
        return responses;
    }

    @Override
    public Menu toUpdateMenu(MenuRequest request, Menu menu) {
        menu.setName(request.getName());
        menu.setPrice(request.getPrice());
        menu.setRating(request.getRating());
        return menu;
    }
}
