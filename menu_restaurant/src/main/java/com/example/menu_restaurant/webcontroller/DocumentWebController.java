package com.example.menu_restaurant.webcontroller;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class DocumentWebController {

    private final DocumentService documentService;

    @GetMapping("/documents-page")
    public String showDocumentsPage(Model model) {
        List<Document> documents = documentService.findAll();
        model.addAttribute("documents", documents);
        return "documents";
    }
}
