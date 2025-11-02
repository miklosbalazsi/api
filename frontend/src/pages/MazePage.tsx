import React from 'react';
import { Card, Empty } from 'antd';

export default function MazePage() {
  return (
    <div>
      <h1>Maze Solver</h1>
      <Card>
        <Empty
          description="Maze solver visualization coming soon"
          style={{ padding: '60px 0' }}
        />
        <p style={{ textAlign: 'center', color: '#999' }}>
          This will show BFS vs DFS pathfinding in a maze
        </p>
      </Card>
    </div>
  );
}
