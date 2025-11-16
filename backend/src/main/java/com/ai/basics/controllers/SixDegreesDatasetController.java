package com.ai.basics.controllers;

import com.ai.basics.models.sixdegrees.Movie;
import com.ai.basics.models.sixdegrees.Person;
import com.ai.basics.services.search.SearchGraph;
import com.ai.basics.services.search.sixdegrees.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dataset")
public class SixDegreesDatasetController {

    private final DatasetService datasetService;

    @Autowired
    public SixDegreesDatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/graph")
    public SearchGraph<Person, Movie> getSearchGraph() {
        return datasetService.getSearchGraph();
    }
}
