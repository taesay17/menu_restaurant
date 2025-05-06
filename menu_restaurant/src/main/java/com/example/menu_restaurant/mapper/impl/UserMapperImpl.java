package com.example.menu_restaurant.mapper.impl;

import com.example.menu_restaurant.mapper.UserMapper;
import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.model.dto.UserRequest;
import com.example.menu_restaurant.model.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    @Override
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }
}
