package com.example.menu_restaurant.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Component
public class SkipPathRequestMatcher {

    private final List<String> pathsToSkip = List.of(
            "/",
            "/login",
            "/register-user",
            "/css/",
            "/js/",
            "/img/",
            "/api/auth/login",
            "/api/auth/refresh"
    );

    public boolean shouldSkip(HttpServletRequest request) {
        String path = request.getRequestURI();
        return pathsToSkip.stream().anyMatch(path::startsWith);
    }
}
