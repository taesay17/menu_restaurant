package com.example.menu_restaurant.service;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.repository.DocumentRepository;

import java.util.Optional;

public interface DocumentService {


    Document createDocument(DocumentRequest documentRequest);

    Document findById(Long id);

    Optional<Document> findByTitle(String title);

    Document getDocumentById(Long id);
    Document save(Document document);

    Document updateDocumentById(Long id, DocumentRequest documentRequest);
    void deleteDocumentByTitle(String title);


    boolean existsByTitle(String title);
}
