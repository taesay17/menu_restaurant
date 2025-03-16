package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/create/{id}")
    public String createDocument(@PathVariable Long id) {
        return documentService.addDocument(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.findById(id));
    }
}
