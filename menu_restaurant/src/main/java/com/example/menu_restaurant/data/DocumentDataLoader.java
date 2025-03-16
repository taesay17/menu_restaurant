package com.example.menu_restaurant.data;

import com.example.menu_restaurant.model.Document;
import com.example.menu_restaurant.repository.DocumentRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentDataLoader {
    private final DocumentRepository documentRepository;

    @PostConstruct
    public void loadDocumentData() {
        if (documentRepository.count() == 0) {
            try (Reader reader = new FileReader(ResourceUtils.getFile("classpath:document_data.csv"))) {
                CsvToBean<Document> csvToBean = new CsvToBeanBuilder<Document>(reader)
                        .withType(Document.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Document> documents = csvToBean.parse();
                documentRepository.saveAll(documents); // Сохраняем сразу список
            } catch (Exception e) {
                System.err.println("Ошибка загрузки CSV: " + e.getMessage());
            }
        }
    }
}
