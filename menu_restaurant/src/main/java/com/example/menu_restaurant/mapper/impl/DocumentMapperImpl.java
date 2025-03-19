package com.example.menu_restaurant.mapper.impl;

import com.example.menu_restaurant.mapper.DocumentMapper;
import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.model.dto.DocumentResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public Document toDocument(DocumentRequest request) {
        Document document = new Document();
        document.setTitle(request.getTitle());
        document.setType(request.getType());
        document.setContent(request.getContent());
        return document;
    }

    @Override
    public DocumentResponse toResponse(Document doсument) {
        DocumentResponse documentResponse = new DocumentResponse();
        documentResponse.setTitle(documentResponse.getTitle());
        documentResponse.setType(documentResponse.getType());
        documentResponse.setContent(documentResponse.getContent());
        return documentResponse;
    }

    @Override
    public Document toUpdateDocument(DocumentRequest request, Document document) {
        document.setTitle(request.getTitle());
        document.setType(request.getType());
        document.setContent(request.getContent());
        return document;

    }
}
