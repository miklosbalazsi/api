package com.ai.basics.services.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchGraph<TState, TAction> {

    List<Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>>> graph = new ArrayList<>();

    public void addSearchNode(SearchNode<TState, TAction> from, List<SearchNode<TState, TAction>> to) {
        // Implementation to add an edge to the graph
        Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>> searchNodeMap = Map.of(from, to);
        graph.add(searchNodeMap);
    }

    public List<Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>>> getGraph() {
        return graph;
    }

    public List<SearchNode<TState, TAction>> getChildren(SearchNode<TState, TAction> parent) {
        for (Map<SearchNode<TState, TAction>, List<SearchNode<TState, TAction>>> edge : graph) {
            if (edge.containsKey(parent)) {
                return edge.get(parent);
            }
        }
        return List.of();
    }

    public List<SearchNode<TState, TAction>> bfsSearch(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode) {

        RemovalPolicy removalPolicy = RemovalPolicy.FIFO;

        System.out.println("BFS search from " + startNode + " to " + goalNode);
        return search(startNode, goalNode, removalPolicy);
    }

    public List<SearchNode<TState, TAction>> dfsSearch(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode) {

        RemovalPolicy removalPolicy = RemovalPolicy.LIFO;

        System.out.println("DFS search from " + startNode + " to " + goalNode);
        return search(startNode, goalNode, removalPolicy);
    }

    private List<SearchNode<TState, TAction>> search(SearchNode<TState, TAction> startNode,
            SearchNode<TState, TAction> goalNode, RemovalPolicy removalPolicy) {

        List<SearchNode<TState, TAction>> pathSearchNodes = new ArrayList<>();

        Frontier<TState, TAction> frontier = new Frontier<>(removalPolicy);
        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            SearchNode<TState, TAction> currentNode = frontier.pop();
            System.out.println("Visiting node: " + currentNode);

            if (currentNode.equals(goalNode)) {
                System.out.println("Goal node found: " + currentNode);
                return List.of(currentNode);
            }

            List<SearchNode<TState, TAction>> children = getChildren(currentNode);
            for (SearchNode<TState, TAction> child : children) {
                frontier.add(child);
            }
        }

        return pathSearchNodes;
    }

    public static <TState, TAction> void printResult(List<SearchNode<TState, TAction>> pathSearchNodes) {
        if (pathSearchNodes.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (SearchNode<TState, TAction> node : pathSearchNodes) {
                System.out.println(node);
            }
        }
    }

}
