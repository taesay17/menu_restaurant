//package com.example.menu_restaurant.repository;
//
//import com.example.menu_restaurant.model.Menu;
//import com.example.menu_restaurant.model.Document;
//import com.example.menu_restaurant.model.dto.MenuRequest;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class MenuRepositoryTest {
//
//    @Autowired
//    private MenuRepository menuRepository;
//
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//    }
//
//    @Test
//    void testMenuRequestValidation_NameTooShort() {
//        // Создаем объект с некорректным именем (менее 2 символов)
//        MenuRequest menuRequest = new MenuRequest();
//        menuRequest.setName("A"); // Название слишком короткое
//        menuRequest.setPrice(10.99);
//        menuRequest.setRating(5);
//        menuRequest.setCategory("Main Course");
//
//        // Проверяем, что валидация не прошла
//        Set<ConstraintViolation<MenuRequest>> violations = validator.validate(menuRequest);
//        assertFalse(violations.isEmpty(), "Валидация должна быть неудачной, так как имя слишком короткое");
//    }
//
//    @Test
//    void testMenuRequestValidation_InvalidRating() {
//        // Создаем объект с некорректным рейтингом (больше 5)
//        MenuRequest menuRequest = new MenuRequest();
//        menuRequest.setName("Valid Menu");
//        menuRequest.setPrice(10.99);
//        menuRequest.setRating(6); // Некорректный рейтинг
//        menuRequest.setCategory("Main Course");
//
//        // Проверяем, что валидация не прошла
//        Set<ConstraintViolation<MenuRequest>> violations = validator.validate(menuRequest);
//        assertFalse(violations.isEmpty(), "Валидация должна быть неудачной, так как рейтинг больше 5");
//    }
//
//    @Test
//    void testMenuRequestValidation_Success() {
//        // Создаем объект с корректными данными
//        MenuRequest menuRequest = new MenuRequest();
//        menuRequest.setName("Valid Menu");
//        menuRequest.setPrice(10.99);
//        menuRequest.setRating(5);
//        menuRequest.setCategory("Main Course");
//
//        // Проверяем, что валидация прошла успешно
//        Set<ConstraintViolation<MenuRequest>> violations = validator.validate(menuRequest);
//        assertTrue(violations.isEmpty(), "Валидация должна пройти успешно, так как данные корректны");
//    }
//
//    // Можно добавить дополнительные тесты для репозитория
//    @Test
//    void findByName_Success() {
//        // Создаем и сохраняем меню в репозиторий
//        Document document = new Document();
//        document.setTitle("Test Document");
//        document.setType("Recipe");
//        document.setContent("Test Content");
//
//        Menu menu = new Menu();
//        menu.setName("Test Menu");
//        menu.setPrice(10.99);
//        menu.setRating(5);
//        menu.setCategory("Main Course");
//        menu.setDocument(document);
//
//        menuRepository.save(menu);
//
//        // Ищем меню по имени
//        Optional<Menu> foundMenus = menuRepository.findByName("Test Menu");
//
//        assertFalse(foundMenus.isEmpty(), "Меню должно быть найдено");
//        assertEquals("Test Menu", foundMenus.get().getName(), "Название меню не совпадает");
//    }
//
//}
