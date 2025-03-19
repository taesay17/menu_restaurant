package com.example.menu_restaurant.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocumentResponse {
    private Long id;
    private String title;
    private String type;
    private String content;
    private LocalDateTime createdAt;
}
