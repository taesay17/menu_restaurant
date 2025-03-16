package com.example.menu_restaurant.mapper;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.dto.MenuResponse;
import com.example.menu_restaurant.model.Menu;

import java.util.List;

public interface MenuMapper {
    Menu toMenu(MenuRequest request);

    MenuResponse toResponse (Menu menu);
    List<MenuResponse> toMenuList(List<Menu> menuList);
    Menu toUpdateMenu(MenuRequest request, Menu menu);
}
