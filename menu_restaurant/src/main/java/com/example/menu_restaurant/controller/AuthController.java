package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.model.dto.AuthRequest;
import com.example.menu_restaurant.model.dto.AuthResponse;
import com.example.menu_restaurant.model.dto.RefreshToken;
import com.example.menu_restaurant.repository.UserRepository;
import com.example.menu_restaurant.security.JwtUtils;
import com.example.menu_restaurant.security.impl.UserDetailsImpl;
import com.example.menu_restaurant.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Пришёл запрос на регистрацию: username=" + user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtUtils.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUser());

        return ResponseEntity.ok(new AuthResponse(token, refreshToken.getToken()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken) {
        return refreshTokenService.findByToken(refreshToken)
                .map(token -> {
                    if (refreshTokenService.isTokenExpired(token)) {
                        return ResponseEntity.badRequest().body("Refresh token expired");
                    }
                    String newAccessToken = jwtUtils.generateToken(
                            new UserDetailsImpl(token.getUser())
                    );
                    return ResponseEntity.ok(new AuthResponse(newAccessToken));
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Invalid refresh token"));
    }
}
