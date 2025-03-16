package com.example.menu_restaurant.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "menu_tb")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private double price;
    private int rating;

    @OneToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;


}
