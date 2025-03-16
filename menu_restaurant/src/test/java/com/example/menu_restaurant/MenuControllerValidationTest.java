package com.example.menu_restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.menu_restaurant.model.dto.MenuRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInvalidMenuCreation() throws Exception {
        MenuRequest request = new MenuRequest();
        request.setName("");
        request.setPrice(-15);
        request.setRating(6);
        request.setCategory("");

        mockMvc.perform(post("/api/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.price").value("Price must be greater than 0"))
                .andExpect(jsonPath("$.rating").value("Rating cannot be more than 5"))
                .andExpect(jsonPath("$.category").value("Category cannot be blank"));
    }
}
