package com.ai.basics.services.search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Abstract class representing a queue data structure.
 */
public class Lifo<TState, TAction> implements SearchQueue<TState, TAction> {
    Deque<SearchNode<TState, TAction>> stack = new ArrayDeque<>();

    @Override
    public void add(SearchNode<TState, TAction> item) {
        stack.push(item);
    }

    @Override
    public SearchNode<TState, TAction> pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
