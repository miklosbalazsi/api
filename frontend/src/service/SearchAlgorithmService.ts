import {Node} from "./Node";
import {QueueFrontier} from "./QueueFrontier";
import {StackFrontier} from "./StackFrontier";

export class SearchAlgorithmService {

    visitedNodes: Node[] = [];
    frontier: StackFrontier | QueueFrontier;

    constructor(frontier: StackFrontier | QueueFrontier) {
        this.frontier = frontier;
    }

    search(maze: string[][], start: { x: number; y: number }, end: { x: number; y: number }) {

        console.log("Search started")

        const startNode = new Node({x: start.x, y: start.y}, null, 'Start', '');
        const endNode = new Node({x: end.x, y: end.y}, null, 'End', '');
        this.frontier.add(startNode);

        while (!this.frontier.isEmpty()) {
            const currentNode = this.frontier.pop();
            this.visitedNodes.push(currentNode);

            console.log("CurrentNode: {} {}", currentNode.state.x, currentNode.state.y);

            // Check if we reached the goal
            if (currentNode.equals(endNode)) {
                return currentNode;
            }

            // Explore neighbors
            const neighbors = this.getNeighbors(currentNode, maze);

            for (const neighbor of neighbors) {
                if (!this.frontier.containsNode(neighbor) && !this.visitedNodes.some(visitedNode => visitedNode.equals(neighbor))) {
                    this.frontier.add(neighbor);
                }
            }
        }
    }

    getNeighbors(node: Node, maze: string[][]): Node[] {
        const neighbors: Node[] = [];
        const directions = [
            {dx: -1, dy: 0}, // Up
            {dx: 1, dy: 0},  // Down
            {dx: 0, dy: -1}, // Left
            {dx: 0, dy: 1}   // Right
        ];

        for (const direction of directions) {
            const newX = node.state.x + direction.dx;
            const newY = node.state.y + direction.dy;

            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length) {
                if (maze[newX][newY] !== '#') {
                    neighbors.push(new Node({x: newX, y: newY}, node));
                }
            }
        }
        return neighbors;
    }
}