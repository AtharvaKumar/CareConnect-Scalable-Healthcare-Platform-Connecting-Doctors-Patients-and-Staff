import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route, Link, Navigate } from 'react-router-dom'
import Doctors from './pages/Doctors'
import Patients from './pages/Patients'
import Appointments from './pages/Appointments'
import Billing from './pages/Billing'
import Login from './pages/Login'

function Layout() {
  return (
    <div style={{fontFamily:'Inter, system-ui', padding:16}}>
      <h1>Hospital Admin</h1>
      <nav style={{display:'flex', gap:12, marginBottom:16}}>
        <Link to="/login">Login</Link>
        <Link to="/doctors">Doctors</Link>
        <Link to="/patients">Patients</Link>
        <Link to="/appointments">Appointments</Link>
        <Link to="/billing">Billing</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Navigate to="/doctors" />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/doctors" element={<Doctors/>} />
        <Route path="/patients" element={<Patients/>} />
        <Route path="/appointments" element={<Appointments/>} />
        <Route path="/billing" element={<Billing/>} />
      </Routes>
    </div>
  )
}

ReactDOM.createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <Layout/>
  </BrowserRouter>
)
