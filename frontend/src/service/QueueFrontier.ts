import {Node} from "./Node";

export class QueueFrontier {
    frontier: any[] = [];

    add(node: any) {
        this.frontier.push(node);
    }

    containsNode(node: Node): boolean {
        return this.frontier.some((node: Node): boolean => node.equals(node));
    }

    isEmpty() {
        return this.frontier.length === 0;
    }

    pop() {
        if (this.isEmpty()) {
            throw new Error("Frontier is empty");
        }
        return this.frontier.shift();  // Removes the First element
    }
}