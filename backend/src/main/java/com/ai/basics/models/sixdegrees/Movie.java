package com.ai.basics.models.sixdegrees;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class Movie {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "year")
    private int year;
}
