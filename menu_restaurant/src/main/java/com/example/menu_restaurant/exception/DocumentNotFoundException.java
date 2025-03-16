package com.example.menu_restaurant.exception;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(Long id) {
        super("Документ с ID " + id + " не найден");
    }
}
