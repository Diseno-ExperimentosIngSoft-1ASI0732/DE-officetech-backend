package com.officetech.officetech.API.bdd;

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
class PostAnswerScenarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void CrearPostYResponder() throws Exception {
        // Arrange: JSON para crear un post y una respuesta
        String postJson = """
            {
                "title": "¿Cómo funciona Spring Boot?",
                "content": "Alguien me puede explicar?",
                "userId": 1
            }
        """;
        String respuestaJson = """
            {
                "postId": 1,
                "content": "Spring Boot hace todo más fácil.",
                "userId": 2
            }
        """;

        // Act: crear el post y luego la respuesta
        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postJson))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(respuestaJson))
                .andExpect(status().isOk());

        // Assert: recuperar las respuestas para el post y validar contenido
        mockMvc.perform(get("/api/v1/posts/1/answers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Spring Boot hace todo más fácil."));
    }
}
