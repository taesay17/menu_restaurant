package com.example.menu_restaurant.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class SkipPathRequestMatcherFilter extends OncePerRequestFilter {

    private final JwtFilter jwtFilter;
    private final List<String> skipPaths;

    public SkipPathRequestMatcherFilter(JwtFilter jwtFilter, List<String> skipPaths) {
        this.jwtFilter = jwtFilter;
        this.skipPaths = skipPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        for (String skipPath : skipPaths) {
            if (path.startsWith(skipPath)) {
                // Пропускаем JWT фильтр
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Если путь не исключён - применяем JWT фильтр
        jwtFilter.doFilter(request, response, filterChain);
    }
}
