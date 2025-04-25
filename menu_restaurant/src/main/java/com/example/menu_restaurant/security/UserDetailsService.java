package com.example.menu_restaurant.security;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
