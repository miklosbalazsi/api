import React, { useEffect, useState } from 'react'

export default function App() {
  const [message, setMessage] = useState<string>('')

  useEffect(() => {
    fetch('/api/hello')
      .then(r => r.json())
      .then(d => setMessage(d.message))
      .catch(() => setMessage('Failed to load'))
  }, [])

  return (
    <div style={{fontFamily: 'Arial, sans-serif', padding: 20}}>
      <h1>Frontend</h1>
      <p>Backend says: <strong>{message}</strong></p>
    </div>
  )
}
