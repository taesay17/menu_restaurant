package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DocumentRepositoryTest {
    @Autowired
    private DocumentRepository documentRepository;

    private Document document;

    @BeforeEach
    void setUp() {
        document = new Document();
        document.setTitle("Recipe 001");
        document.setType("Recipe");
        document.setContent("Instructions for Beshbarmak");
        document.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void findByTitle_Success() {
        Optional<Document> foundDocument = documentRepository.findByTitle("Recipe 001");
        assertTrue(foundDocument.isPresent());
        assertEquals("Recipe 001", foundDocument.get().getTitle());
    }

    @Test
    void findById_Success() {
        Optional<Document> foundDocument = documentRepository.findById(Long.valueOf(2));
        assertTrue(foundDocument.isPresent());
        assertEquals(2, foundDocument.get().getId());
    }

    @Test
    void deleteDocumentByTitle_Success() {
        documentRepository.deleteByTitle("Recipe 001");
        Optional<Document> foundDocument = documentRepository.findByTitle("Recipe 001");
        assertFalse(foundDocument.isPresent());
    }

}
