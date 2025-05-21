package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.model.Role;
import com.example.menu_restaurant.repository.RoleRepository;
import com.example.menu_restaurant.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    @Override
    public void initRoles() {
        // Проверяем, существует ли роль ROLE_USER
        if (!roleRepository.existsByName("ROLE_USER")) {
            // Если роли нет, создаём её
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        // Аналогично добавляем роль ROLE_ADMIN
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }
    }
}
