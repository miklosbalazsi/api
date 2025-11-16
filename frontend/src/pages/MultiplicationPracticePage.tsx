import React, { useState } from 'react';
import { Card, Input, Button, message } from 'antd';

export default function MultiplicationPracticePage() {
  const [num1, setNum1] = useState(generateRandomNumber());
  const [num2, setNum2] = useState(generateRandomNumber());
  const [userAnswer, setUserAnswer] = useState('');
  const [score, setScore] = useState(0);
  const [statistics, setStatistics] = useState<Record<string, { success: number; fail: number }>>({});
  const recentPairs: string[] = [];

  function generateRandomNumber() {
    return Math.floor(Math.random() * 13); // Random number between 0 and 12
  }

  const generateRandomPair = () => {
    const pairs = Object.keys(statistics);
    if (pairs.length === 0) {
      // No statistics yet, generate completely random pair
      return [generateRandomNumber(), generateRandomNumber()];
    }

    // Filter out the last 10 pairs
    const availablePairs = pairs.filter((pair) => !recentPairs.includes(pair));

    // Find pairs with no statistics or the highest fail rate
    let worstPair = null;
    let highestFailRate = -1;

    for (const pair of availablePairs) {
      const { success, fail } = statistics[pair];
      const total = success + fail;
      const failRate = total > 0 ? fail / total : 0;

      if (failRate > highestFailRate) {
        highestFailRate = failRate;
        worstPair = pair;
      }
    }

    // Generate a pair with no statistics first
    const noStatsPair = availablePairs.find((pair) => !statistics[pair]);
    if (noStatsPair) {
      const [num1, num2] = noStatsPair.split('x').map(Number);
      return [num1, num2];
    }

    // Every tenth pair can be from the worst statistics
    if (Math.random() < 0.1 && worstPair) {
      const [num1, num2] = worstPair.split('x').map(Number);
      return [num1, num2];
    }

    // Otherwise, generate a completely random pair
    return [generateRandomNumber(), generateRandomNumber()];
  };

  const handleCheckAnswer = () => {
    const correctAnswer = num1 * num2;
    const key = `${num1}x${num2}`;

    setStatistics((prevStats) => {
      const currentStats = prevStats[key] || { success: 0, fail: 0 };
      if (parseInt(userAnswer) === correctAnswer) {
        message.success('Correct!');
        setScore(score + 1);
        return {
          ...prevStats,
          [key]: { ...currentStats, success: currentStats.success + 1 },
        };
      } else {
        message.error(`Incorrect! The correct answer was ${correctAnswer}.`);
        return {
          ...prevStats,
          [key]: { ...currentStats, fail: currentStats.fail + 1 },
        };
      }
    });

    // Update recent pairs
    recentPairs.push(key);
    if (recentPairs.length > 10) {
      recentPairs.shift();
    }

    const [newNum1, newNum2] = generateRandomPair();
    setNum1(newNum1);
    setNum2(newNum2);
    setUserAnswer('');
  };

  const renderStatistics = () => {
    return Object.entries(statistics).map(([key, { success, fail }]) => {
      const total = success + fail;
      const successRate = ((success / total) * 100).toFixed(2);
      const failRate = ((fail / total) * 100).toFixed(2);
      return (
        <div key={key}>
          {key}: {successRate}% success, {failRate}% fail
        </div>
      );
    });
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', gap: '16px' }}>
      <Card title="Multiplication Practice" style={{ maxWidth: 400, textAlign: 'center' }}>
        <h2>
          {num1} × {num2} = ?
        </h2>
        <Input
          type="number"
          value={userAnswer}
          onChange={(e) => setUserAnswer(e.target.value)}
          placeholder="Enter your answer"
          style={{ marginBottom: 16 }}
          onKeyDown={(e) => {
            if (e.key === 'Enter') {
              handleCheckAnswer();
            }
          }}
        />
        <Button type="primary" onClick={handleCheckAnswer}>
          Submit
        </Button>
        <div style={{ marginTop: 16 }}>
          <strong>Score: {score}</strong>
        </div>
      </Card>
      <Card title="Statistics" style={{ maxWidth: 400, textAlign: 'left' }}>
        {renderStatistics()}
      </Card>
    </div>
  );
}
