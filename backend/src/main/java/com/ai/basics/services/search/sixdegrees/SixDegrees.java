package com.ai.basics.services.search.sixdegrees;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.basics.models.sixdegrees.Movie;
import com.ai.basics.models.sixdegrees.Person;
import com.ai.basics.models.sixdegrees.Star;
import com.ai.basics.services.search.SearchGraph;
import com.ai.basics.services.search.SearchNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class SixDegrees {

    private final ReadCsvService readCsvService;

    public void findConnection(String personA, String personB) {

        // ReadCsvFiles
        List<Movie> movies = readCsvService.readMovies(ReadCsvService.DatasetSize.SMALL);
        List<Person> people = readCsvService.readPeople(ReadCsvService.DatasetSize.SMALL);
        List<Star> stars = readCsvService.readStars(ReadCsvService.DatasetSize.SMALL);

        log.info("Movies loaded: {}", movies);
        log.info("People loaded: {}", people);
        log.info("Stars loaded: {}", stars);

        // Generate SearchGraph
        SearchGraph<Person, Movie> graphSearch = buildGraph(movies, people, stars);

        SearchNode<Person, Movie> startNode = new SearchNode<Person, Movie>(getPeopleByName(people, personA), null, null);
        SearchNode<Person, Movie> goalNode = new SearchNode<Person, Movie>(getPeopleByName(people, personB), null, null);

        SearchNode<Person, Movie> resultNode = graphSearch.bfsSearch(startNode, goalNode);

        SearchGraph.printResult(graphSearch, startNode, resultNode);
    }

    private SearchGraph<Person, Movie> buildGraph(List<Movie> movies, List<Person> people, List<Star> stars) {
        SearchGraph<Person, Movie> graph = new SearchGraph<>();

        for (Star star : stars) {
            log.info(star);
            Person person = getPeopleById(people, star.getPersonId());
            Movie movie = getMovieById(movies, star.getMovieId());

            SearchNode<Person, Movie> personNode = new SearchNode<Person, Movie>(person, movie, null);

            // Get All people acted in this movie
            List<SearchNode<Person, Movie>> actors = getAllPeopleInMovie(stars, people, movie).stream().map(p -> new SearchNode<>(p, movie, personNode))
                    .toList();

            // Add SearchNode to graph
            graph.addSearchNode(personNode, actors);
        }

        // Print Number of Nodes in the graph
        log.info("Number of nodes in the graph: {}", graph.getGraph().size());

        return graph;
    }

    private List<Person> getAllPeopleInMovie(List<Star> stars, List<Person> people, Movie movie) {
        List<Person> actors = stars.stream().filter(s -> s.getMovieId() == movie.getId()).map(s -> getPeopleById(people, s.getPersonId())).toList();
        return actors;
    }

    private Person getPeopleById(List<Person> people, int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private Person getPeopleByName(List<Person> people, String name) {
        return people.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private Movie getMovieById(List<Movie> movies, int id) {
        return movies.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

}
