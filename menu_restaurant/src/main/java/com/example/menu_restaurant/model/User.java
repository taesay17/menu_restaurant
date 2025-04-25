package com.example.menu_restaurant.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    // геттеры/сеттеры
}

