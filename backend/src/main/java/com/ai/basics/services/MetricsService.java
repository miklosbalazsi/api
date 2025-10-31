/**
 * Service class to access Springboot Meterics.
 * Uses Lombok for boilerplate code reduction.
 * 
 */
package com.ai.basics.services;
import lombok.RequiredArgsConstructor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;  

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final MeterRegistry meterRegistry;

    /**
     * Return the uptime of the application in unix milliseconds.
     * @return uptime in unix milliseconds 
     */
    public long getUptime() {
        return (long) meterRegistry.get("process.uptime").gauge().value();
    }

}
