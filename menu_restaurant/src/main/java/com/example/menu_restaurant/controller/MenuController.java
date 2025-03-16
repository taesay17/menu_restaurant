package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor

@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/create")
    public Menu createMenuList(@RequestBody MenuRequest request) {
        return menuService.createMenuList(request);
    }
    @GetMapping("/get/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }
    @PutMapping("/update/{id}")
    public Menu updateById(@PathVariable Long id, @RequestBody MenuRequest request) {
        return menuService.updateMenuById(id, request);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMenuById(@PathVariable Long id) {
        menuService.deleteMenuById(id);
    }
    @GetMapping("/get/all")
    public List<Menu> getAllMenu(){
        return menuService.findAll();
    }
    @GetMapping("/get/{name}")
    public Optional<Menu> getMenu(@PathVariable String name) {
        return menuService.getMenuByName(name);
    }
    @GetMapping("/find/{rating}")
    public Optional<Menu> findMenuRating(@PathVariable int rating) {
        return menuService.findByRating(rating);
    }
    @DeleteMapping("/deleteName/{name}")
    public String deleteMenuByName(@PathVariable String name) {
        return menuService.deleteMenuByName(name);
    }
}
