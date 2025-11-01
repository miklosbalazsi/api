package com.ai.basics.services.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchNode<TState, TAction> {

    // State
    private TState state;

    // Action
    private TAction action;

    // Parent Node
    private SearchNode<TState, TAction> parent;

    public String toString() {
        return "SearchNode{state=" + state + ", action=" + action + "}";
    }

    // Equals and hashCode based on state
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SearchNode<?, ?>))
            return false;
        SearchNode<?, ?> other = (SearchNode<?, ?>) obj;
        return state.equals(other.state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }
}
