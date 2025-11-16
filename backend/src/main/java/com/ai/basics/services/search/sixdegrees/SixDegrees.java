package com.ai.basics.services.search.sixdegrees;

import com.ai.basics.models.sixdegrees.Movie;
import com.ai.basics.models.sixdegrees.Person;
import com.ai.basics.models.sixdegrees.Star;
import com.ai.basics.services.search.SearchGraph;
import com.ai.basics.services.search.SearchNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SixDegrees {


    private final DatasetService datasetService;

    public void findConnection(String personA, String personB) {

        // ReadCsvFiles
        datasetService.loadDataset("SMALL");

        // Generate SearchGraph
        SearchGraph<Person, Movie> graphSearch = datasetService.getSearchGraph();

        SearchNode<Person, Movie> startNode = new SearchNode<Person, Movie>(datasetService.getPeopleByName(personA), null, null);
        SearchNode<Person, Movie> goalNode = new SearchNode<Person, Movie>(datasetService.getPeopleByName(personB), null, null);

        SearchNode<Person, Movie> resultNode = graphSearch.bfsSearch(startNode, goalNode);

        SearchGraph.printResult(graphSearch, startNode, resultNode);
    }
}
