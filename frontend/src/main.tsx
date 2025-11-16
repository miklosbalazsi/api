import React from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import DashboardLayout from './layouts/DashboardLayout'
import HomePage from './pages/HomePage'
import GraphPage from './pages/GraphPage'
import MazePage from './pages/MazePage'
import MultiplicationPracticePage from './pages/MultiplicationPracticePage'

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<DashboardLayout />}>
          <Route index element={<HomePage />} />
          <Route path="graph" element={<GraphPage />} />
          <Route path="maze" element={<MazePage />} />
          <Route path="multiplication-practice" element={<MultiplicationPracticePage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
)
