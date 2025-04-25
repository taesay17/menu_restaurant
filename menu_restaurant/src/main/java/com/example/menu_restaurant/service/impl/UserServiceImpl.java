package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.repository.UserRepository;
import com.example.menu_restaurant.security.UserDetails;
import com.example.menu_restaurant.security.impl.UserDetailsImpl;
import com.example.menu_restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return (UserDetails) new UserDetailsImpl(user);
    }
}
