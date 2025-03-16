package com.example.menu_restaurant.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionTest {

    private final GlobalException globalException = new GlobalException();

    @Test
    void testHandleMenuException() {
        MenuException exception = new MenuException("Menu not found", HttpStatus.NOT_FOUND);

        ResponseEntity<Object> response = globalException.handleMenuException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Menu not found", response.getBody());
    }
}
