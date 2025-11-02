package com.ai.basics.services.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Removal policies for the frontier.
 */
@AllArgsConstructor
@Getter
public enum RemovalPolicy {

    FIFO("BFS", "Queue"),

    LIFO("DFS", "Stack");

    private final String name;
    private final String dataStructure;
}