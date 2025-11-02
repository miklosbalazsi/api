package com.ai.basics.services.search;

import java.util.HashSet;
import java.util.Set;

public class Frontier<TState, TAction> {

    /**
     * The removal policy for the frontier. Can be FIFO (BFS, queue) or LIFO (DFS,
     * stack).
     */
    private RemovalPolicy removalPolicy;

    /**
     * The underlying queue data structure used for the frontier.
     */
    private SearchQueue<TState, TAction> queue;

    /**
     * List of the Visited states
     */
    private Set<TState> visitedStates;

    public Frontier(RemovalPolicy policy) {
        this.visitedStates = new HashSet<>();
        setRemovalPolicy(policy);
    }

    void setRemovalPolicy(RemovalPolicy policy) {
        switch (policy) {
        case FIFO:
            // Use Fifo implementation
            queue = new Fifo<TState, TAction>();
            break;
        case LIFO:
            // Use Lifo implementation
            queue = new Lifo<TState, TAction>();
            break;
        }
    }

    public String getRemovalPolicyName() {
        return removalPolicy.getName();
    }

    public SearchNode<TState, TAction> pop() {
        return queue.pop();
    }

    public void add(SearchNode<TState, TAction> node) {

        if (!visitedStates.contains(node.getState())) {
            queue.add(node);
            visitedStates.add(node.getState());
        }

    }

    public int getVisitedNodesCount() {
        return visitedStates.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
