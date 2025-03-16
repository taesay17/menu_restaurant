package com.example.menu_restaurant.exception;

import com.example.menu_restaurant.model.dto.MenuRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MenuExceptionTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testValidationErrorsOnCreateMenu() throws Exception {
        MenuRequest invalidRequest = new MenuRequest(); // Missing fields (invalid)

        mockMvc.perform(post("/api/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists());
    }

    @Test
    void testMenuNotFoundException() throws Exception {
        mockMvc.perform(get("/api/menu/999")) // Non-existent ID
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Menu not found"));
    }
}
