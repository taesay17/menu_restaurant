package com.example.menu_restaurant.webcontroller;

import com.example.menu_restaurant.model.User;
import com.example.menu_restaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserWebController {

    private final UserService userService;

    @GetMapping("/users-page")
    public String showUsersPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";  // => src/main/resources/templates/users.html
    }
}
