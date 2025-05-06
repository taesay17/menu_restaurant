package com.example.menu_restaurant.csv;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.service.MenuService;
import com.opencsv.CSVReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.List;

@Component
public class CsvDataLoader {

    private final MenuService menuService;

    public CsvDataLoader(MenuService menuService) {
        this.menuService = menuService;
    }

    @Bean
    CommandLineRunner loadMenusFromCsv() {
        return args -> {
            try (CSVReader reader = new CSVReader(new InputStreamReader(
                    new ClassPathResource("menu.csv").getInputStream()))) {

                List<String[]> records = reader.readAll();

                records.stream().skip(1).forEach(data -> {
                    String name = data[0];
                    double price = Double.parseDouble(data[1]);
                    int rating = Integer.parseInt(data[2]);
                    String category = data[3];

                    Menu menu = new Menu();
                    menu.setName(name);
                    menu.setPrice(price);
                    menu.setRating(rating);
                    menu.setCategory(category);

                    menuService.save(menu);
                });

                System.out.println("✅ Меню успешно загружено из CSV!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
