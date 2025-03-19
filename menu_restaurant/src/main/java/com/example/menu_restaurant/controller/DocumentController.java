package com.example.menu_restaurant.controller;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.model.dto.DocumentRequest;
import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/document")
public class DocumentController {
    private final DocumentService documentService;


    @PostMapping("/create")
    public Document createDocument(@RequestBody DocumentRequest request) {
        return documentService.createDocument(request);
    }



    @GetMapping("/get/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }

    @PutMapping("/get/{id}")
    public Document updateDocumentById(@PathVariable Long id, @RequestBody DocumentRequest request) {
        return documentService.updateDocumentById(id, request);
    }

    @GetMapping("/find/{title}")
    public Optional<Document> findByTitle(@PathVariable String title) {
        return documentService.findByTitle(title);
    }


    @DeleteMapping("/deleteDocument/{title}")
    public void deleteDocumentByTitle(@PathVariable String title) {
        documentService.deleteDocumentByTitle(title);
    }



}
