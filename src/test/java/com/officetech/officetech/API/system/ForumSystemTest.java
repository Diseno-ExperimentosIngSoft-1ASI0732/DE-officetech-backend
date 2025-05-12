package com.officetech.officetech.API.system;

import com.officetech.officetech.API.forum.interfaces.rest.resources.CreateNewPostResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForumSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void debeCrearYListarPostCorrectamente() {
        // Arrange
        CreateNewPostResource post = new CreateNewPostResource(1L, "Post del sistema", "Texto de prueba");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateNewPostResource> request = new HttpEntity<>(post, headers);

        // Act
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/api/v1/posts", request, String.class);
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/v1/posts", String.class);

        // Assert
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertTrue(getResponse.getBody().contains("Post del sistema"));
    }
}