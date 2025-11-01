package com.ai.basics.utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.opencsv.bean.CsvToBeanBuilder;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class CsvReader {

    @SneakyThrows
    public static <T> List<T> readCsv(String filePath, Class<T> type, boolean hasHeader) {

        log.info("Reading CSV file: {}", filePath);

        ClassPathResource resource = new ClassPathResource(filePath);
        log.info("Resource found: {}", resource.exists());

        Reader reader = new InputStreamReader(resource.getInputStream());

        CsvToBeanBuilder<T> builder = new CsvToBeanBuilder<T>(reader).withType(type).withIgnoreLeadingWhiteSpace(true);

        if (!hasHeader) {
            // Use @CsvBindByPosition instead of @CsvBindByName in your model
            builder.withSkipLines(0); // Don't skip any lines
        }

        return builder.build().parse();
    }

    // Convenience method - defaults to having header
    public static <T> List<T> readCsv(String filePath, Class<T> type) {
        return readCsv(filePath, type, true);
    }
}
