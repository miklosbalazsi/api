package com.ai.basics.services.search;

public interface SearchQueue<TState, TAction> {

    void add(SearchNode<TState, TAction> item);

    SearchNode<TState, TAction> pop();

    boolean isEmpty();

}
