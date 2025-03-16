package com.example.menu_restaurant.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "document_tb")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "content")
    private String content;

    private LocalDateTime createdAt= LocalDateTime.now();

    @OneToOne(mappedBy = "document")
    private Menu menu;
}
