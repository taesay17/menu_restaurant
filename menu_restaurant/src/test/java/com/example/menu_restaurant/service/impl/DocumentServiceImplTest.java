package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.exception.DocumentNotFoundException;
import com.example.menu_restaurant.exception.MenuException;
import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.Menu;
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
    private MenuRepository menuRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    private Document document;
    private Menu menu;

    @BeforeEach
    void setUp() {
        document = new Document();
        document.setId(1L);
        document.setTitle("Test Document");

        menu = new Menu();
        menu.setId(1L);
    }

    @Test
    void addDocument_Success() {
        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(documentRepository.save(any(Document.class))).thenReturn(document);
        when(menuRepository.save(menu)).thenReturn(menu);

        String result = documentService.addDocument(1L);

        assertEquals("Document added succesfully", result);
        assertNotNull(menu.getDocument());
        verify(documentRepository, times(1)).save(any(Document.class));
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    void addDocument_MenuNotFound() {
        when(menuRepository.findById(1L)).thenReturn(Optional.empty());

        MenuException exception = assertThrows(MenuException.class, () -> documentService.addDocument(1L));
        assertEquals("Menu product not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void findById_Success() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));

        Document found = documentService.findById(1L);
        assertEquals(document, found);
    }

    @Test
    void findById_NotFound() {
        when(documentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DocumentNotFoundException.class, () -> documentService.findById(1L));
    }

    @Test
    void saveDocument() {
        when(documentRepository.save(document)).thenReturn(document);

        Document saved = documentService.save(document);
        assertEquals(document, saved);
    }

    @Test
    void deleteDocument_Success() {
        when(documentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(documentRepository).deleteById(1L);

        assertDoesNotThrow(() -> documentService.delete(1L));
        verify(documentRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteDocument_NotFound() {
        when(documentRepository.existsById(1L)).thenReturn(false);

        assertThrows(DocumentNotFoundException.class, () -> documentService.delete(1L));
    }
}
