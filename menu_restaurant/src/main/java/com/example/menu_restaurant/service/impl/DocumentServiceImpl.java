package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.exception.DocumentNotFoundException;
import com.example.menu_restaurant.exception.MenuException;
import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.Menu;
import com.example.menu_restaurant.repository.DocumentRepository;
import com.example.menu_restaurant.repository.MenuRepository;
import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final MenuRepository menuRepository;

    @Override
    public String addDocument(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new MenuException("Menu product not found", HttpStatus.NOT_FOUND));
        Document document = new Document();
        document = documentRepository.save(document);
        menu.setDocument(document);
        menuRepository.save(menu);
        return "Document added succesfully";
    }

    @Override
    public Document findById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void delete(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new DocumentNotFoundException(id);
        }
        documentRepository.deleteById(id);
    }


}
