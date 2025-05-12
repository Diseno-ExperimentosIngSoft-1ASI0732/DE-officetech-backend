package com.officetech.officetech.API.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void CrearYListarPosts() throws Exception {
        // Arrange: preparar el JSON del nuevo post
        String nuevoPostJson = """
            {
                "title": "Título de integración",
                "content": "Este es un post de prueba",
                "userId": 1
            }
        """;

        // Act: ejecutar la llamada POST y luego la GET
        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoPostJson))
                .andExpect(status().isOk());

        // Assert: verificar que el endpoint de listado devuelve un array
        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
