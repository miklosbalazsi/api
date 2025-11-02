import React from 'react';
import { Card, Empty } from 'antd';

export default function GraphPage() {
  return (
    <div>
      <h1>Six Degrees of Separation</h1>
      <Card>
        <Empty
          description="Graph visualization coming soon"
          style={{ padding: '60px 0' }}
        />
        <p style={{ textAlign: 'center', color: '#999' }}>
          This will show the actor connection graph with BFS pathfinding
        </p>
      </Card>
    </div>
  );
}
