import {Node} from "./Node";

export class StackFrontier {

    frontier: Node[] = [];

    constructor() {
    }

    add(node: Node): void {
        if (!this.containsNode(node)) {
            this.frontier.push(node);
        }
    }

    containsNode(node: Node): boolean {
        return this.frontier.some((frontierNode: Node): boolean => frontierNode.equals(node));
    }

    isEmpty() {
        return this.frontier.length === 0;
    }

    pop() {
        if (this.isEmpty()) {
            throw new Error("Frontier is empty");
        }
        return this.frontier.pop();   // Removes the Last element
    }
}

export function queueFrontier() {
    const frontier: any[] = [];

    function add(node: any) {
        frontier.push(node);
    }

    function containsNode(node: Node): boolean {
        return frontier.some((node: Node): boolean => node.equals(node));
    }

    function isEmpty() {
        return frontier.length === 0;
    }

    function pop() {
        if (isEmpty()) {
            throw new Error("Frontier is empty");
        }
        return frontier.shift();  // Removes the First element
    }
}