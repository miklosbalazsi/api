import { render, screen } from '@testing-library/react'
import App from './App'

test('renders frontend header', () => {
  render(<App />)
  const el = screen.getByText(/Frontend/i)
  expect(el).toBeInTheDocument()
})
