//package com.example.menu_restaurant.repository;
//
//import com.example.menu_restaurant.model.Document;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class DocumentRepositoryTest {
//
//    @Autowired
//    private DocumentRepository documentRepository;
//
//    private Document document;
//
//    @BeforeEach
//    void setUp() {
//        // Создаем документ для тестов
//        document = new Document();
//        document.setTitle("Test Document");
//        document.setType("Recipe");
//        document.setContent("Test Content");
//        documentRepository.save(document);
//    }
//
//    @Test
//    void findByTitle_Success() {
//        // Ищем документ по названию
//        Document foundDocument = documentRepository.findByTitle("Test Document").orElse(null);
//
//        assertNotNull(foundDocument, "Документ должен быть найден");
//        assertEquals("Test Document", foundDocument.getTitle(), "Название документа не совпадает");
//    }
//
//    @Test
//    void findByTitle_NotFound() {
//        // Ищем документ с несуществующим названием
//        Document foundDocument = documentRepository.findByTitle("Non-existent Document").orElse(null);
//
//        assertNull(foundDocument, "Документ не должен быть найден");
//    }
//
//    @Test
//    void deleteByTitle_Success() {
//        // Удаляем документ по названию
//        documentRepository.deleteByTitle("Test Document");
//
//        // Проверяем, что документ больше не существует
//        Document foundDocument = documentRepository.findByTitle("Test Document").orElse(null);
//        assertNull(foundDocument, "Документ должен быть удален");
//    }
//
//    @Test
//    void deleteByTitle_NotFound() {
//        // Удаляем несуществующий документ
//        documentRepository.deleteByTitle("Non-existent Document");
//
//        // Пытаемся найти его
//        Document foundDocument = documentRepository.findByTitle("Non-existent Document").orElse(null);
//        assertNull(foundDocument, "Документ не должен быть найден");
//    }
//
//    @Test
//    void existsByTitle_True() {
//        // Проверяем существование документа по названию
//        boolean exists = documentRepository.existsByTitle("Test Document");
//
//        assertTrue(exists, "Документ должен существовать");
//    }
//
//    @Test
//    void existsByTitle_False() {
//        // Проверяем существование несуществующего документа
//        boolean exists = documentRepository.existsByTitle("Non-existent Document");
//
//        assertFalse(exists, "Документ не должен существовать");
//    }
//}
