package com.ai.basics.models.sixdegrees;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class Star {

    @CsvBindByName(column = "person_id")
    private int personId;

    @CsvBindByName(column = "movie_id")
    private int movieId;

}
