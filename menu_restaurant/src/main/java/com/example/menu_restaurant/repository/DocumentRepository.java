package com.example.menu_restaurant.repository;

import com.example.menu_restaurant.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {


    Optional<Document> findByTitle(String title);

    void deleteByTitle(String title);

    Object existsByTitle(String testDocument);
}
