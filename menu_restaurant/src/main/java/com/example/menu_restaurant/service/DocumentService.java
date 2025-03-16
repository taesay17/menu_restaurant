package com.example.menu_restaurant.service;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.repository.DocumentRepository;

public interface DocumentService {


    String addDocument(Long id);

    Document findById(Long id);
    Document save(Document document);
    void delete(Long id);

}
