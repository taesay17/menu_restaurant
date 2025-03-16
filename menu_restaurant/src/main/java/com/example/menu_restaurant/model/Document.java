package com.example.menu_restaurant.model;

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

    private String title;
    private String type;
    private String content;

    private LocalDateTime createdAt= LocalDateTime.now();

    @OneToOne(mappedBy = "document")
    private Menu menu;
}
