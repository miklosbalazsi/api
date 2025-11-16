declare module 'react-force-3d-graph' {
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

  export interface GraphProps {
    graphData: GraphData;
    nodeAutoColorBy?: string;
    linkDirectionalParticles?: number;
    linkDirectionalParticleSpeed?: (link: GraphLink) => number;
    [key: string]: any;
  }

  export class ForceGraph3D extends Component<GraphProps> {}

  export default ForceGraph3D;
}
