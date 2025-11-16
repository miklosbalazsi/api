package com.ai.basics.services.search.sixdegrees;

import com.ai.basics.models.sixdegrees.Movie;
import com.ai.basics.models.sixdegrees.Person;
import com.ai.basics.models.sixdegrees.Star;
import com.ai.basics.services.search.SearchGraph;
import com.ai.basics.services.search.SearchNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class DatasetService {

    private SearchGraph<Person, Movie> searchGraph;

    List<Movie> movies;
    List<Person> people;
    List<Star> stars;

    private final ReadCsvService readCsvService;

    public void loadDataset(String datasetType) {
        ReadCsvService.DatasetSize datasetSize;

        if ("SMALL".equalsIgnoreCase(datasetType)) {
            datasetSize = ReadCsvService.DatasetSize.SMALL;
        } else if ("LARGE".equalsIgnoreCase(datasetType)) {
            datasetSize = ReadCsvService.DatasetSize.SMALL;
        } else {
            throw new IllegalArgumentException("Invalid dataset type: " + datasetType);
        }
        // ReadCsvFiles
        movies = readCsvService.readMovies(datasetSize);
        people = readCsvService.readPeople(datasetSize);
        stars = readCsvService.readStars(datasetSize);

        log.info("Movies loaded: {}", movies);
        log.info("People loaded: {}", people);
        log.info("Stars loaded: {}", stars);

        // Generate SearchGraph
        searchGraph = buildGraph(movies, people, stars);
    }

    public Person getPeopleByName(String name) {
        return people.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private SearchGraph<Person, Movie> buildGraph(List<Movie> movies, List<Person> people, List<Star> stars) {
        SearchGraph<Person, Movie> graph = new SearchGraph<>();

        for (Star star : stars) {
            log.info(star);
            Person person = getPeopleById(star.getPersonId());
            Movie movie = getMovieById(star.getMovieId());

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
        List<Person> actors = stars.stream().filter(s -> s.getMovieId() == movie.getId()).map(s -> getPeopleById(s.getPersonId())).toList();
        return actors;
    }

    private Person getPeopleById(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private Movie getMovieById(int id) {
        return movies.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

}
