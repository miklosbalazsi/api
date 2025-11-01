package com.ai.basics.services.search.sixdegrees;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.basics.models.sixdegrees.Movie;
import com.ai.basics.models.sixdegrees.Person;
import com.ai.basics.models.sixdegrees.Star;
import com.ai.basics.utils.CsvReader;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
public class ReadCsvService extends CsvReader {

    private final String moviesFile = "movies.csv";
    private final String peopleFile = "people.csv";
    private final String starsFile = "stars.csv";

    public List<Movie> readMovies(DatasetSize datasetSize) {
        String filePath = datasetSize.getPath() + "/" + moviesFile;
        return readCsv(filePath, Movie.class, true);
    }

    public List<Person> readPeople(DatasetSize datasetSize) {
        String filePath = datasetSize.getPath() + "/" + peopleFile;
        return readCsv(filePath, Person.class, true);
    }

    public List<Star> readStars(DatasetSize datasetSize) {
        String filePath = datasetSize.getPath() + "/" + starsFile;
        return readCsv(filePath, Star.class, true);
    }

    @AllArgsConstructor
    @Getter
    enum DatasetSize {

        SMALL("sixdegrees/small"),
        LARGE("sixdegrees/large");

        private final String path;
    }

}