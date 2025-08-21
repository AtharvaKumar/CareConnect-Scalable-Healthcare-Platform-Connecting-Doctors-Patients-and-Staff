import React, { useState } from 'react'
import axios from '../utils/axios'

export default function Login(){
  const [username, setUsername] = useState('demo')
  const [password, setPassword] = useState('demo')
  const [service, setService] = useState('doctor') // login via doctor-service

  const submit = async (e) => {
    e.preventDefault()
    const base = service === 'doctor' ? '/doctor' : '/doctor'
    const { data } = await axios.post(`${base}/api/auth/login`, { username, password })
    localStorage.setItem('token', data.token)
    alert('Token saved to localStorage')
  }

  return (
    <form onSubmit={submit} style={{display:'grid', gap:8, maxWidth:320}}>
      <h2>Login</h2>
      <input value={username} onChange={e=>setUsername(e.target.value)} placeholder="username"/>
      <input value={password} type="password" onChange={e=>setPassword(e.target.value)} placeholder="password"/>
      <button>Get Token</button>
    </form>
  )
}
