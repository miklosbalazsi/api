import React, {useState} from 'react';
import {Card, Col, Input, message, Row} from 'antd';

export default function MultiplicationPracticePage() {
    const MIN = 2;
    const MAX = 12;
    const [num1, setNum1] = useState(getRandomIntInRange());
    const [num2, setNum2] = useState(getRandomIntInRange());
    const [userAnswer, setUserAnswer] = useState('');
    const [score, setScore] = useState(0);
    const [statistics, setStatistics] = useState<Record<string, { success: number; fail: number, successRate: number }>>(generateInitialStatistics);

    function generateInitialStatistics() {
        const stats: Record<string, { success: number; fail: number, successRate: number }> = {};

        for (let a = MIN; a <= MAX; a++) {
            for (let b = MIN; b <= MAX; b++) {
                const key = `${a}x${b}`;
                stats[key] = {success: 0, fail: 0, successRate: 0};
            }
        }
        return stats;
    }

    function getRandomIntInRange(): number {
        return Math.floor(Math.random() * (MAX - MIN + 1)) + MIN;
    }

    const generateRandomPair = () => {
        const pairs = Object.entries(statistics)
            .filter(([, {successRate}]) => successRate < 90); // Filter pairs with successRate below 50

        if (pairs.length > 0) {
            const randomIndex = Math.floor(Math.random() * pairs.length);
            const [key] = pairs[randomIndex];
            const [num1, num2] = key.split('x').map(Number);
            return [num1, num2];
        }

        // Fallback to completely random pair if no pairs match the criteria
        return [getRandomIntInRange(), getRandomIntInRange()];
    };

    const calculateSuccessRate = (
        e: { success: number; fail: number; successRate: number },
        success: boolean
    ): { success: number; fail: number; successRate: number } => {
        const updatedSuccess = success ? e.success + 1 : e.success;
        const updatedFail = success ? e.fail : e.fail + 1;
        const totalAttempts = updatedSuccess + updatedFail;

        const updatedSuccessRate = totalAttempts > 0
            ? (updatedSuccess / totalAttempts) * 100
            : 0;

        return {
            success: updatedSuccess,
            fail: updatedFail,
            successRate: updatedSuccessRate,
        };
    }

    const handleCheckAnswer = () => {
        const correctAnswer: number = num1 * num2;
        const key: string = `${num1}x${num2}`;

        const isCorrect: boolean = parseInt(userAnswer) === correctAnswer;
        if (isCorrect) {
            message.success('Correct!');
            setScore((prev) => prev + 1);
        } else {
            message.error(`Incorrect! The correct answer was ${correctAnswer}.`);
        }

        setStatistics((prevStats) => {
            const currentStats = prevStats[key] || {success: 0, fail: 0, successRate: 0};
            const isCorrect: boolean = parseInt(userAnswer) === correctAnswer;
            return {
                ...prevStats,
                [key]: calculateSuccessRate(currentStats, isCorrect)
            };
        });

        const [newNum1, newNum2] = generateRandomPair();
        setNum1(newNum1);
        setNum2(newNum2);
        setUserAnswer('');
    };

    return (
        <div style={{padding: '16px'}}>
            <Row justify="center" style={{marginBottom: '16px'}}>
                <Col span={24} style={{display: 'flex', justifyContent: 'center'}}>
                    <Card title="Multiplication Practice" style={{maxWidth: 400, textAlign: 'center'}}>
                        <h2>
                            {num1} × {num2} = ?
                        </h2>
                        <Input
                            type="number"
                            value={userAnswer}
                            onChange={(e) => setUserAnswer(e.target.value)}
                            placeholder="Enter your answer"
                            style={{marginBottom: 16}}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    handleCheckAnswer();
                                }
                            }}
                        />
                    </Card>
                </Col>
            </Row>
            <Row justify="center" style={{marginTop: '16px'}}>
                <Col span={24} style={{display: 'flex', justifyContent: 'center'}}>
                    <Card title="Multiplication Matrix" style={{maxWidth: 800, textAlign: 'center'}}>
                        <table style={{borderCollapse: 'collapse', width: '100%'}}>
                            <thead>
                            <tr>
                                <th style={{border: '1px solid black', padding: '8px'}}>×</th>
                                {Array.from({length: MAX - MIN + 1}, (_, i) => MIN + i).map((col) => (
                                    <th key={col} style={{border: '1px solid black', padding: '8px'}}>{col}</th>
                                ))}
                            </tr>
                            </thead>
                            <tbody>
                            {Array.from({length: MAX - MIN + 1}, (_, rowIndex) => {
                                const row = MIN + rowIndex;
                                return (
                                    <tr key={row}>
                                        <td style={{border: '1px solid black', padding: '8px', fontWeight: 'bold'}}>{row}</td>
                                        {Array.from({length: MAX - MIN + 1}, (_, colIndex) => {
                                            const col: number = MIN + colIndex;
                                            const key = `${row}x${col}`;
                                            const successRate: number = statistics[key]?.successRate || 0;
                                            let backgroundColor: string = 'red';
                                            if (successRate >= 90) {
                                                backgroundColor = 'green';
                                            } else if (successRate >= 50) {
                                                backgroundColor = 'yellow';
                                            }
                                            return (
                                                <td
                                                    key={key}
                                                    style={{
                                                        border: '1px solid black',
                                                        padding: '8px',
                                                        backgroundColor,
                                                        color: 'white',
                                                        textAlign: 'center',
                                                    }}
                                                >
                                                    {successRate.toFixed(0)}%
                                                </td>
                                            );
                                        })}
                                    </tr>
                                );
                            })}
                            </tbody>
                        </table>
                    </Card>
                </Col>
            </Row>
        </div>
    )
}
