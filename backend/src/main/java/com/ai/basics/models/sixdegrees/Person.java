package com.ai.basics.models.sixdegrees;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class Person {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "birth")
    private int birthYear;

}
