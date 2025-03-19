package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

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

            // Сохраняем документ в базу данных
            document = documentRepository.saveAndFlush(document);
        }

        @Test
        void findByTitle_Success() {
            Optional<Document> foundDocument = documentRepository.findByTitle("Recipe 001");

            assertTrue(foundDocument.isPresent(), "Документ должен существовать в БД");
            assertEquals("Recipe 001", foundDocument.get().getTitle());
        }

        @Test
        void findById_Success() {
            Optional<Document> foundDocument = documentRepository.findById(document.getId());

            assertTrue(foundDocument.isPresent(), "Документ должен существовать в БД");
            assertEquals(document.getId(), foundDocument.get().getId());
        }

        @Test
        @Transactional
        void deleteDocumentByTitle_Success() {
            Document newDocument = new Document();
            newDocument.setTitle("Test Document");
            newDocument.setType("Test Type");
            newDocument.setContent("Test Content");
            newDocument.setCreatedAt(LocalDateTime.now());
            documentRepository.saveAndFlush(newDocument);

            Optional<Document> savedDocument = documentRepository.findByTitle("Test Document");
            System.out.println("Документ перед удалением: " + savedDocument.isPresent());
            assertTrue(savedDocument.isPresent(), "Документ должен существовать перед удалением");

            documentRepository.deleteByTitle("Test Document");

            Optional<Document> deletedDocument = documentRepository.findByTitle("Test Document");
            System.out.println("Документ после удаления: " + deletedDocument.isPresent());
            assertFalse(deletedDocument.isPresent(), "Документ должен быть удален");
        }
    }
