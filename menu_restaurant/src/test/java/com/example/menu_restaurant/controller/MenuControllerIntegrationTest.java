//package com.example.menu_restaurant.controller;
//
//import com.example.menu_restaurant.model.dto.MenuRequest;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.math.BigDecimal;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class MenuControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        // Setup data if needed (e.g., insert test data into H2)
//    }
//
//    @Test
//    void testCreateMenuItem() throws Exception {
//        MenuRequest request = new MenuRequest();
//        request.setName("Pizza");
//        request.setPrice(340);
//        request.setRating(4);
//        request.setCategory("Italian");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/menu")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("Pizza"))
//                .andExpect(jsonPath("$.price").value(340));
//    }
//
//    @Test
//    void testGetAllMenuItems() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/menu")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
