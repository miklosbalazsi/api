export class Node {
    state: any;
    label?: string;
    actions: string;
    parent: Node | null;

    constructor(state: any, parent: Node | null, label?: string, actions?: string) {
        this.state = state;
        this.label = label || '';
        this.actions = actions || '';
        this.parent = parent;
    }

    equals(other: Node): boolean {
        if (typeof this.state === 'object' && typeof other.state === 'object') {
            return JSON.stringify(this.state) === JSON.stringify(other.state);
        }
        return this.state === other.state;
    }
}
