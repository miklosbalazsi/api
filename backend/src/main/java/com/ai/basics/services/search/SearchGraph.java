package com.ai.basics.services.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchGraph<TState, TAction> {

    Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>> graph = new HashMap<>();

    public void addSearchNode(SearchNode<TState, TAction> from, List<SearchNode<TState, TAction>> to) {
        // Implementation to add an edge to the graph
        graph.put(from, to);
    }

    public Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>> getGraph() {
        return graph;
    }

    public List<SearchNode<TState, TAction>> getChildren(SearchNode<TState, TAction> parent) {
        if (graph.isEmpty()) {
            throw new IllegalStateException("Graph is empty");
        }

        // Get Children nodes
        return graph.getOrDefault(parent, List.of());
    }

    public SearchNode<TState, TAction> bfsSearch(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode) {

        RemovalPolicy removalPolicy = RemovalPolicy.FIFO;

        log.info("BFS search from {} to {}", startNode, goalNode);
        return search(startNode, goalNode, removalPolicy);
    }

    public SearchNode<TState, TAction> dfsSearch(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode) {

        RemovalPolicy removalPolicy = RemovalPolicy.LIFO;

        log.info("DFS search from {} to {}", startNode, goalNode);
        return search(startNode, goalNode, removalPolicy);
    }

    private SearchNode<TState, TAction> search(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode, RemovalPolicy removalPolicy) {

        Frontier<TState, TAction> frontier = new Frontier<>(removalPolicy);
        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            SearchNode<TState, TAction> currentNode = frontier.pop();
            log.info("Visiting node: {}", currentNode);

            if (currentNode.equals(goalNode)) {
                log.info("Goal node found: {}", currentNode);
                return currentNode;
            }

            for (SearchNode<TState, TAction> child : getChildren(currentNode)) {
                frontier.add(child);
            }
        }

        // Print Number of Visited Nodes
        log.info("No path found from {} to {}", startNode, goalNode);
        log.info("Number of visited nodes: {}", frontier.getVisitedNodesCount());
        return null;
    }

    /**
     * Print the result path by taking the goal node and follow the path (get parent
     * SearchNodes) back to the start node
     */
    public static <TState, TAction> void printResult(SearchGraph<TState, TAction> graph,
            SearchNode<TState, TAction> startNode, SearchNode<TState, TAction> goalNode) {

        if (goalNode == null) {
            return;
        }

        log.info("Printing result path from {} to {}", startNode, goalNode);

        SearchNode<TState, TAction> currentNode = goalNode;
        while (currentNode.getParent() != null) {
            // Print the current node
            log.info("Current Node: {}", currentNode);
            currentNode = currentNode.getParent();
        }
    }

}
