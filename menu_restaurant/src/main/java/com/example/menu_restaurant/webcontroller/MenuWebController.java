package com.example.menu_restaurant.webcontroller;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MenuWebController {

    private final MenuService menuService;

    @GetMapping("/menus-page")
    public String showMenusPage(Model model) {
        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);
        return "menus";
    }
}
