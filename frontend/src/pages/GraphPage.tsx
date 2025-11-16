import React, { useEffect, useState } from 'react';
import { Card, Empty, Spin } from 'antd';

export default function GraphPage() {
  const [graphData, setGraphData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('/api/dataset/graph')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch graph data');
        }
        return response.json();
      })
      .then((data) => {
        setGraphData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error(error);
        setLoading(false);
      });
  }, []);

  return (
    <div>
      <h1>Six Degrees of Separation</h1>
      <Card>
        {loading ? (
          <Spin tip="Loading graph data..." style={{ display: 'block', textAlign: 'center', padding: '60px 0' }} />
        ) : graphData ? (
          <pre style={{ whiteSpace: 'pre-wrap', wordWrap: 'break-word' }}>{JSON.stringify(graphData, null, 2)}</pre>
        ) : (
          <Empty
            description="No graph data available"
            style={{ padding: '60px 0' }}
          />
        )}
      </Card>
    </div>
  );
}
