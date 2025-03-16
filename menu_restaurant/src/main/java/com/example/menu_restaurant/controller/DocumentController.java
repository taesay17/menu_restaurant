package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/create/{id}")
    public String createDocument(@PathVariable Long id) {
        return documentService.addDocument(id);
    }
}
