package com.example.menu_restaurant.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)

public class GlobalException {
    @ExceptionHandler(MenuException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMenuException(MenuException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
