package com.example.menu_restaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    // ✅ Секретный ключ (лучше потом вынести в application.properties)
    private final String jwtSecret = "secretKeyForJWTGeneration";

    // ✅ Время жизни токена (1 день = 86400000 миллисекунд)
    private final long jwtExpirationMs = 86400000;

    /**
     * Извлекает имя пользователя (subject) из токена
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлекает дату истечения токена
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Универсальный метод для извлечения любого значения из Claims
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Извлекает все Claims из токена
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Проверяет, истёк ли токен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Генерирует новый JWT токен для пользователя
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // username в качестве subject
                .setIssuedAt(new Date()) // дата выпуска
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // дата истечения
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // подпись
                .compact();
    }

    /**
     * Проверяет валидность токена
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
