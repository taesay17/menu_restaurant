package com.example.menu_restaurant.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MenuException extends RuntimeException{
    private HttpStatus status;

    public  MenuException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    @Override
    public String toString() {
        return "Message: " + getMessage() + ", Status: " + getStatus();
    }
}
