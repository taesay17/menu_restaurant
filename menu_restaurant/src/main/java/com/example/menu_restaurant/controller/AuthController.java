package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.model.dto.AuthRequest;
import com.example.menu_restaurant.model.dto.AuthResponse;
import com.example.menu_restaurant.repository.UserRepository;
import com.example.menu_restaurant.service.JwtService;
import com.example.menu_restaurant.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        String username = authRequest.getUsername();

        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
//        if (userRepository.existsByUsername(request.getUsername())) {
//            return ResponseEntity.badRequest().body("Пользователь уже существует.");
//        }
//
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Пользователь успешно зарегистрирован.");
//    }
}
