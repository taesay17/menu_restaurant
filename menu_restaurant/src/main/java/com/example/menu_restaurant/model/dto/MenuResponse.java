package com.example.menu_restaurant.model.dto;

import lombok.Data;

@Data
public class MenuResponse {
    private String name;
    private double price;
    private String category;
}
