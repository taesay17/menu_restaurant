package com.example.menu_restaurant.data;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.repository.DocumentRepository;
import com.example.menu_restaurant.repository.MenuRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final DocumentRepository documentRepository;
    private final MenuRepository menuRepository;

    @PostConstruct
    public void init() {
        Menu Besh = new Menu();
        Besh.setName("Beshbarmak");
        Besh.setPrice(200);
        Besh.setRating(5);
        Besh.setCategory("Kyrgyz");

        Menu Pizza = new Menu();
        Pizza.setPrice(340);
        Pizza.setRating(4);
        Pizza.setCategory("Italian");


    }


}
