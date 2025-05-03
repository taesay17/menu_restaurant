package com.example.menu_restaurant.model.dto;

public class AuthResponse {
    private String token;

    public AuthResponse(String token, String refreshTokenToken) {}

    public AuthResponse(String token) { this.token = token; }

    public String getToken() { return token; }
}
