package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.exception.DocumentNotFoundException;
import com.example.menu_restaurant.exception.MenuException;
import com.example.menu_restaurant.mapper.DocumentMapper;
import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.repository.DocumentRepository;
import com.example.menu_restaurant.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private MenuRepository menuRepository;

    private DocumentRequest documentRequest;

    @InjectMocks
    private DocumentServiceImpl documentService;

    private Document document;

    @BeforeEach
    void setUp() {
        document = new Document();
        document.setId(1L);
        document.setTitle("Test Document");

        documentRequest = new DocumentRequest();
        documentRequest.setTitle("Recipe 001");
    }


    @Test
    void createDocument() {
        when(documentMapper.toDocument(documentRequest)).thenReturn(document);
        when(documentRepository.save(document)).thenReturn(document);

        Document createdDocument = documentService.createDocument(documentRequest);

        assertNotNull(createdDocument);
        verify(documentRepository, times(1)).save(document);
    }

    @Test
    void findById_Success() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));

        Document found = documentService.findById(1L);
        assertEquals(document, found);
    }



    @Test
    void saveDocument() {
        when(documentRepository.save(document)).thenReturn(document);

        Document saved = documentService.save(document);
        assertEquals(document, saved);
    }

//    @Test
//    void deleteDocumentByTitle_Success() {
//        when(documentRepository.existsByTitle("Test Document")).thenReturn(true);
//
//        doNothing().when(documentRepository).deleteByTitle("Test Document");
//
//        assertDoesNotThrow(() -> documentService.deleteDocumentByTitle("Test Document"));
//
//        verify(documentRepository, times(1)).deleteByTitle("Test Document");
//    }



}
