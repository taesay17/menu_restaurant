package com.example.menu_restaurant.data;

import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.repository.MenuRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuDataLoader {

    private final MenuRepository menuRepository;

    @PostConstruct
    public void loadMenuData() {
        if (menuRepository.count() == 0) {
            try (Reader reader = new FileReader(ResourceUtils.getFile("classpath:menu_data.csv"))) {
                CsvToBean<Menu> csvToBean = new CsvToBeanBuilder<Menu>(reader)
                        .withType(Menu.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Menu> menus = csvToBean.parse();
                menuRepository.saveAll(menus);
            } catch (Exception e) {
                System.err.println("Ошибка загрузки CSV: " + e.getMessage());
            }
        }


    }
}