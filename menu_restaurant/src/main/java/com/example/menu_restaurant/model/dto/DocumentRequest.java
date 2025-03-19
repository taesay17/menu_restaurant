package com.example.menu_restaurant.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocumentRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Content cannot be blank")
    private String content;
}
