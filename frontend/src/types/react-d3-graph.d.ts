declare module 'react-d3-graph' {
  import { Component } from 'react';

  export interface GraphNode {
    id: string;
    [key: string]: any;
  }

  export interface GraphLink {
    source: string;
    target: string;
    [key: string]: any;
  }

  export interface GraphData {
    nodes: GraphNode[];
    links: GraphLink[];
  }

  export interface GraphConfig {
    nodeHighlightBehavior?: boolean;
    node?: {
      color?: string;
      size?: number;
      highlightStrokeColor?: string;
      [key: string]: any;
    };
    link?: {
      highlightColor?: string;
      [key: string]: any;
    };
    [key: string]: any;
  }

  export interface GraphProps {
    id: string;
    data: GraphData;
    config: GraphConfig;
    [key: string]: any;
  }

  export class Graph extends Component<GraphProps> {}

  export default Graph;
}
