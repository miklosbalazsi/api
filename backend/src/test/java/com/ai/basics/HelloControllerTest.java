package com.ai.basics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.ai.basics.services.MetricsService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MetricsService metricsService;

    @Test
    void helloReturnsMessage() throws Exception {
        when(metricsService.getUptime()).thenReturn(3600L); // mock 1 hour uptime

        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello World"))
                .andExpect(jsonPath("$.uptime").value("01:00:00"));
    }
}
