package com.ai.basics;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloEndpointReturns200AndJson() {
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
            "/api/hello",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Map<String, String>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, String> body = Objects.requireNonNull(response.getBody());
        assertThat(body).containsEntry("message", "Hello World");
        String uptime = body.get("uptime");
        assertThat(uptime).isNotNull();
        assertThat(uptime).matches("\\d{2}:\\d{2}:\\d{2}");
    }
}
