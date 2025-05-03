package com.example.menu_restaurant.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    // Современная конструкторная инъекция
    public JwtFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // 1. Проверяем есть ли токен и начинается ли с Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtils.extractUsername(token);
        }

        // 2. Если токен и имя пользователя получены, и пользователь ещё не аутентифицирован
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 3. Загружаем пользователя
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 4. Проверяем действителен ли токен
            if (jwtUtils.validateToken(token, userDetails)) {

                // 5. Создаём аутентификацию
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 6. Устанавливаем аутентификацию в контекст
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 7. Передаём запрос дальше по цепочке фильтров
        filterChain.doFilter(request, response);
    }
}
