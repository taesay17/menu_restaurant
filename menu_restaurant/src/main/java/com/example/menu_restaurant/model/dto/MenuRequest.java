package com.example.menu_restaurant.model.dto;

import lombok.Data;

@Data
public class MenuRequest {

    private String name;
    private double price;
    private int rating;
}
