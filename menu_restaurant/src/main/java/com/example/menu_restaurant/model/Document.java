package com.example.menu_restaurant.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "document_tb")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "document")
    private Menu menu;
}
