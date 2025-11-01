package com.ai.basics.services.search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Abstract class representing a queue data structure.
 */
public class Fifo<TState, TAction> implements SearchQueue<TState, TAction> {

    Queue<SearchNode<TState, TAction>> queue = new ArrayDeque<>();

    @Override
    public void add(SearchNode<TState, TAction> item) {
        queue.offer(item);
    }

    public SearchNode<TState, TAction> pop() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
