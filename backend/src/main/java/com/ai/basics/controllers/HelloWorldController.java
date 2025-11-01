package com.ai.basics.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.basics.services.MetricsService;
import com.ai.basics.utils.DateTimeUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    private final MetricsService metricsService;

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello World", "uptime",
                DateTimeUtil.formatUptimeFromMillis(metricsService.getUptime()));
    }
}
