package com.example.menu_restaurant.mapper;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.model.dto.DocumentResponse;

public interface DocumentMapper {
    Document toDocument(DocumentRequest request);

    DocumentResponse toResponse(Document document);

    Document toUpdateDocument(DocumentRequest request, Document document);
}
