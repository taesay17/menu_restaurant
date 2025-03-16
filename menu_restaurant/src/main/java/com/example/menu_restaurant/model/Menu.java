package com.example.menu_restaurant.model;

import com.opencsv.bean.CsvBindByName;
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

    @CsvBindByName(column = "name")
    @Column(unique = true, nullable = false)
    private String name;

    @CsvBindByName(column = "price")
    private double price;

    @CsvBindByName(column = "rating")
    private int rating;

    @CsvBindByName(column = "category")
    private String category;

    @OneToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;


}
