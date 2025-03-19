package com.example.menu_restaurant.mapper;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.model.dto.DocumentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DocumentMapperImplTest {

    private DocumentMapperImpl documentMapper;

    @BeforeEach
    void setUp() {
        documentMapper = new DocumentMapperImpl();
    }

    @Test
    void toDocument_Success() {
        // Создаем объект request
        DocumentRequest request = new DocumentRequest();
        request.setTitle("Test Title");
        request.setType("Recipe");
        request.setContent("Test Content");

        Document document = documentMapper.toDocument(request);

        assertNotNull(document, "Document не должен быть null");
        assertEquals(request.getTitle(), document.getTitle());
        assertEquals(request.getType(), document.getType());
        assertEquals(request.getContent(), document.getContent());
        assertNotNull(document.getCreatedAt(), "Дата создания не должна быть null");
    }

    @Test
    void toResponse_Success() {
        Document document = new Document();
        document.setTitle("Test Title");
        document.setType("Recipe");
        document.setContent("Test Content");
        document.setCreatedAt(LocalDateTime.now());

        // Преобразуем Document в DocumentResponse
        DocumentResponse documentResponse = documentMapper.toResponse(document);

        // Проверяем, что объект DocumentResponse не null
        assertNotNull(documentResponse, "DocumentResponse не должен быть null");
        // Проверяем, что поля правильно скопировались
        assertEquals(document.getTitle(), documentResponse.getTitle());
        assertEquals(document.getType(), documentResponse.getType());
        assertEquals(document.getContent(), documentResponse.getContent());
    }

    @Test
    void toUpdateDocument_Success() {
        // Создаем существующий объект Document
        Document existingDocument = new Document();
        existingDocument.setTitle("Old Title");
        existingDocument.setType("Old Type");
        existingDocument.setContent("Old Content");
        existingDocument.setCreatedAt(LocalDateTime.now());

        // Создаем request для обновления
        DocumentRequest updateRequest = new DocumentRequest();
        updateRequest.setTitle("New Title");
        updateRequest.setType("New Type");
        updateRequest.setContent("New Content");

        // Обновляем существующий документ
        Document updatedDocument = documentMapper.toUpdateDocument(updateRequest, existingDocument);

        // Проверяем, что объект обновлен
        assertNotNull(updatedDocument, "Updated document не должен быть null");
        assertEquals(updateRequest.getTitle(), updatedDocument.getTitle());
        assertEquals(updateRequest.getType(), updatedDocument.getType());
        assertEquals(updateRequest.getContent(), updatedDocument.getContent());
        // Проверяем, что дата создания не изменилась
        assertEquals(existingDocument.getCreatedAt(), updatedDocument.getCreatedAt(), "Дата создания должна остаться неизменной");
    }
}
