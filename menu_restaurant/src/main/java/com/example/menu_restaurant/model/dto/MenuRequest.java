package com.example.menu_restaurant.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MenuRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private int rating;

    @NotBlank(message = "Category cannot be blank")
    private String category;
}
