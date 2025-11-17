import React from 'react';
import {Card} from 'antd';
import {SearchAlgorithmService as Search} from '../service/SearchAlgorithmService';
import {Node} from '../service/Node';
import {StackFrontier} from "../service/StackFrontier";

export default function MazePage() {

    const maze: string[][] = [
        ['0', '#', '#', '0', '0'],
        ['0', '#', '0', '1', '#'],
        ['0', '#', '#', '1', '0'],
        ['1', '1', '#', '0', '#'],
        ['0', '0', '1', '1', '#'],
    ];

    const start = {x: 0, y: 0};
    const end = {x: 4, y: 4};

    const searchService = new Search(new StackFrontier());
    const result: Node = searchService.search(maze, start, end);

    return (
        <div>
            <h1>Maze Solver</h1>
            <Card title={result ? 'Path Found!' : 'No Path Found'}>
                <table style={{borderCollapse: 'collapse', margin: 'auto'}}>
                    <tbody>
                    {maze.map((row, rowIndex) => (
                        <tr key={rowIndex}>
                            {row.map((cell, colIndex) => {
                                const isStart = rowIndex === start.x && colIndex === start.y;
                                const isEnd = rowIndex === end.x && colIndex === end.y;
                                return (
                                    <td
                                        key={colIndex}
                                        style={{
                                            width: '30px',
                                            height: '30px',
                                            border: '1px solid black',
                                            backgroundColor: isStart
                                                ? 'green'
                                                : isEnd
                                                    ? 'red'
                                                    : cell === '#'
                                                        ? 'black'
                                                        : 'white',
                                        }}
                                    ></td>
                                );
                            })}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </Card>
        </div>
    );
}
