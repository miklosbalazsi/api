package com.ai.basics.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ai.basics.services.search.sixdegrees.SixDegrees;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    SixDegrees sixdegrees;

    @Override
    public void run(String... args) throws Exception {
        log.info("SixDegrees started - executing startup logic");

        sixdegrees.findConnection("Kevin Bacon", "Tom Hanks");

        log.info("SixDegrees startup logic completed");

    }
}