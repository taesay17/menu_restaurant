//package com.example.menu_restaurant.security;
//
//import com.example.menu_restaurant.model.User;
//import com.example.menu_restaurant.repository.UserRepository;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
//
//    private final JwtUtils jwtUtils;
//    private final UserRepository userRepository;
//
//    public OAuth2SuccessHandler(JwtUtils jwtUtils, UserRepository userRepository) {
//        this.jwtUtils = jwtUtils;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication)
//            throws IOException, ServletException {
//
//        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//        String email = oauthUser.getEmail();
//
//        Optional<User> existingUser = userRepository.findByEmail(email);
//        User user = existingUser.orElseGet(() -> {
//            User newUser = new User();
//            newUser.setEmail(email);
//            newUser.setUsername(email);
//            newUser.setRoles(Set.of());
//            return userRepository.save(newUser);
//        });
//
//        // Преобразуем роли в строку, разделенную запятой
//        String roles = user.getRoles().stream()
//                .map(role -> role.getName())  // Преобразуем каждый объект Role в его имя
//                .collect(Collectors.joining(","));  // Соединяем их через запятую
//
//        String token = jwtUtils.createToken(user.getEmail(), roles);
//
//        response.setContentType("application/json");
//        response.getWriter().write("{\"token\": \"" + token + "\"}");
//    }
//}
