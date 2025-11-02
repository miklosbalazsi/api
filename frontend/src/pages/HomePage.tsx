import React, { useEffect, useState } from 'react';
import { Card, Row, Col, Statistic } from 'antd';
import { ClockCircleOutlined, ApiOutlined } from '@ant-design/icons';

interface HelloResponse {
  message: string;
  uptime: string;
}

export default function HomePage() {
  const [data, setData] = useState<HelloResponse | null>(null);
  const [error, setError] = useState<string>('');

  useEffect(() => {
    const controller = new AbortController();

    fetch('/api/hello', { signal: controller.signal })
      .then(r => r.json())
      .then(d => setData(d))
      .catch(err => {
        if (err.name !== 'AbortError') {
          setError('Failed to load');
        }
      });

    return () => controller.abort();
  }, []);

  return (
    <div>
      <h1>Dashboard</h1>
      <p>Welcome to the Search Algorithm Visualizer</p>

      <Row gutter={16} style={{ marginTop: 24 }}>
        <Col span={8}>
          <Card>
            <Statistic
              title="Backend Status"
              value={error ? 'Error' : data ? data.message : 'Loading...'}
              prefix={<ApiOutlined />}
              valueStyle={{ color: error ? '#cf1322' : data ? '#3f8600' : '#999' }}
            />
          </Card>
        </Col>
        <Col span={8}>
          <Card>
            <Statistic
              title="Server Uptime"
              value={data?.uptime || '00:00:00'}
              prefix={<ClockCircleOutlined />}
            />
          </Card>
        </Col>
      </Row>

      <Card style={{ marginTop: 24 }} title="Available Visualizations">
        <ul>
          <li><strong>Six Degrees Graph:</strong> Visualize actor connections through movies</li>
          <li><strong>Maze Solver:</strong> Watch BFS and DFS algorithms solve mazes in real-time</li>
        </ul>
      </Card>
    </div>
  );
}
