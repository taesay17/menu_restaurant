package com.example.menu_restaurant.model.dto;

import lombok.Data;

@Data
public class MenuResponse {
    private Long id;
    private String name;
    private double price;
    private String category;
}
