package com.example.menu_restaurant.service.impl;

import com.example.menu_restaurant.mapper.DocumentMapper;
import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.repository.DocumentRepository;
import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public Document createDocument(DocumentRequest request) {
        Document document = documentMapper.toDocument(request);
        return documentRepository.save(document);
    }


    @Override
    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public Document updateDocumentById(Long id, DocumentRequest request) {
        Document document = documentRepository.findById(id).orElse(null);
        document = documentMapper.toUpdateDocument(request, document);
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocumentByTitle(String title) {
        documentRepository.deleteByTitle(title);
    }

    @Override
    public Optional<Document> findByTitle(String title) {
        return documentRepository.findByTitle(title);
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }
    @Override
    public boolean existsByTitle(String title) {
        return (boolean) documentRepository.existsByTitle(title);
    }

}
