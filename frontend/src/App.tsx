import React, { useEffect, useState } from 'react'

interface HelloResponse {
  message: string
  uptime: string
}

export default function App() {
  const [data, setData] = useState<HelloResponse | null>(null)
  const [error, setError] = useState<string>('')

  useEffect(() => {
    fetch('/api/hello')
      .then(r => r.json())
      .then(d => setData(d))
      .catch(() => setError('Failed to load'))
  }, [])

  return (
    <div style={{fontFamily: 'Arial, sans-serif', padding: 20}}>
      <h1>Frontend</h1>
      {error ? (
        <p style={{color: 'red'}}>{error}</p>
      ) : data ? (
        <>
          <p>Backend says: <strong>{data.message}</strong></p>
          <p>Uptime: <strong>{data.uptime}</strong></p>
        </>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  )
}
