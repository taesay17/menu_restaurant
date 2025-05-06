package com.example.menu_restaurant.mapper;

import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.model.dto.UserRequest;
import com.example.menu_restaurant.model.dto.UserResponse;

public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toResponse(User user);
}
